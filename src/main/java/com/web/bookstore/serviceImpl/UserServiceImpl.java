package com.web.bookstore.serviceimpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Map;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;

import com.web.bookstore.model.User;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.Order;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.UserService;
import com.web.bookstore.dto.UserDTO;
import com.web.bookstore.dao.UserDAO;
import com.web.bookstore.dao.BookDAO;
import com.web.bookstore.dao.OrderDAO;
import com.web.bookstore.dto.GetUserListOk;

import org.springframework.data.domain.Page;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final OrderDAO orderDAO;
    private final BookDAO bookDAO;
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, AuthService authService, BCryptPasswordEncoder passwordEncoder,
            OrderDAO orderDAO, BookDAO bookDAO) {
        this.userDAO = userDAO;
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.orderDAO = orderDAO;
        this.bookDAO = bookDAO;
    }

    public User findUserById(Integer id) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NoSuchElementException("User not found for ID: " + id);
        }

        return optionalUser.get();
    }

    public ResponseDTO updateUserInfo(
            UpdateUserInfoRequestDTO updateUserInfoRequestDTO,
            User requestUser) throws AuthenticationException {
        String name = updateUserInfoRequestDTO.getName();
        Optional<User> optionalUser = userDAO.findByName(name);
        if (optionalUser.isPresent() && !(optionalUser.get().getId() == requestUser.getId())) {
            throw new IllegalArgumentException("Username already exists");
        }
        requestUser.setName(updateUserInfoRequestDTO.getName());
        requestUser.setDescription(updateUserInfoRequestDTO.getDescription());

        userDAO.save(requestUser);
        return new ResponseDTO(true, "Update user info successfully");
    }

    public ResponseDTO changePassword(User user, String oldPassword, String newPassword) {

        Optional<Auth> optionalAuth = authService.findByUser(user);

        if (optionalAuth.isEmpty()) {
            throw new NoSuchElementException("Auth not found for user: " + user.getId());
        }
        if (!passwordEncoder.matches(oldPassword, optionalAuth.get().getPassword())) {
            System.out.println("Old password is incorrect");
            return new ResponseDTO(false, "Old password is incorrect");
        }

        optionalAuth.get().setPassword(newPassword);
        userDAO.save(user);
        return new ResponseDTO(true, "Change password successfully");
    }

    public GetUserListOk getUserList(Integer pageSize, Integer pageIndex, String keyWord, Integer id) {

        PageRequest pageable = PageRequest.of(pageIndex, pageSize);

        if (id != -1) {
            Optional<User> optionalUser = userDAO.findById(id);
            if (optionalUser.isEmpty()) {
                throw new NoSuchElementException("User not found for ID: " + id);
            }
            List<User> userList = new ArrayList<>();
            userList.add(optionalUser.get());
            // covert to UserDTO
            List<UserDTO> userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());
            return new GetUserListOk(userDTOList, 1);
        }

        Page<User> userPage = userDAO.findByNameContaining(keyWord, pageable);
        List<User> userList = userPage.stream().collect(Collectors.toList());
        // covert to UserDTO
        List<UserDTO> userDTOList = userList.stream().map(UserDTO::new).collect(Collectors.toList());
        return new GetUserListOk(userDTOList, (int) userPage.getTotalElements());
    }

    public ResponseDTO banUser(User targetUser) {
        targetUser.setStatus(1);
        userDAO.save(targetUser);
        return new ResponseDTO(true, "Ban user successfully");
    }

    public ResponseDTO unbanUser(User targetUser) {
        targetUser.setStatus(0);
        userDAO.save(targetUser);
        return new ResponseDTO(true, "Unban user successfully");
    }

    public GetUserListOk getSalesRankList(Integer pageIndex, Integer pageSize, String startTime, String endTime) {
        // Convert startTime and endTime to Instant
        Instant startInstant = Instant.EPOCH;
        Instant endInstant = Instant.now();

        try {
            if (startTime != null && !startTime.isEmpty() && !startTime.equals("undefined")) {
                startInstant = Instant.parse(startTime);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse start time: " + e.getMessage());
        }

        try {
            if (endTime != null && !endTime.isEmpty() && !endTime.equals("undefined")) {
                endInstant = Instant.parse(endTime);
            }
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse end time: " + e.getMessage());
        }

        // Find all orders in the time range
        List<Order> orders = orderDAO.findAllByCreatedAtBetween(startInstant, endInstant);

        // Calculate the total price for each user
        Map<User, Long> userTotalPrice = new HashMap<>();
        for (Order order : orders) {
            User user = order.getUser();
            long totalPrice = order.getItems().stream()
                    .mapToLong(item -> (item.getBookPrice() * item.getBookDiscount() / 10) * item.getNumber())
                    .sum();
            userTotalPrice.put(user, userTotalPrice.getOrDefault(user, 0L) + totalPrice);
        }

        // Sort the users by total price
        List<User> sortedUsers = userTotalPrice.entrySet().stream()
                .sorted(Map.Entry.<User, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Get the page of users
        int start = pageIndex * pageSize;
        int end = Math.min(start + pageSize, sortedUsers.size());
        List<User> userList = sortedUsers.subList(start, end);

        // Convert to UserDTO with the data of total price
        List<UserDTO> userDTOList = userList.stream()
                .map(user -> new UserDTO(user, userTotalPrice.get(user)))
                .collect(Collectors.toList());

        return new GetUserListOk(userDTOList, sortedUsers.size());
    }

    public void save(User user) {
        userDAO.save(user);
    }
}
