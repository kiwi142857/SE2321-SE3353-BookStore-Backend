package com.web.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.NoSuchElementException;

import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.dto.UpdateUserInfoRequestDTO;
import com.web.bookstore.dto.UserDTO;
import com.web.bookstore.exception.UserBannedException;
import com.web.bookstore.model.User;
import com.web.bookstore.dto.PasswordChangeRequestDTO;
import com.web.bookstore.service.UserService;
import com.web.bookstore.util.SessionUtils;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserProfile(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new UserDTO(service.findUserById(id)));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMyProfile() {

        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            return ResponseEntity.ok(new UserDTO(user));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/me")
    public ResponseEntity<Object> updateUserProfile(
            @RequestBody UpdateUserInfoRequestDTO updateUserInfoRequestDTO) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            return ResponseEntity.ok(service.updateUserInfo(updateUserInfoRequestDTO, user));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/me/password")
    public ResponseEntity<Object> changePassword(
            @RequestBody PasswordChangeRequestDTO request) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            return ResponseEntity.ok(service.changePassword(user, request.getOldPassword(), request.getNewPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, e.getMessage()));
        }

    }

    // 管理员获取用户列表
    @GetMapping("/list")
    public ResponseEntity<Object> getUserList(Integer pageSize, Integer pageIndex, String keyWord, Integer id) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            return ResponseEntity.ok(service.getUserList(pageSize, pageIndex, keyWord, id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    // 获取时间范围内的用户消费榜，支持分页
    @GetMapping("/rank/sales")
    public ResponseEntity<Object> getSalesRankList(Integer pageIndex, Integer pageSize, String startTime,
            String endTime) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            return ResponseEntity.ok(service.getSalesRankList(pageIndex, pageSize, startTime, endTime));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    // 管理员封禁用户/解封用户
    @PutMapping("/ban/{id}")
    public ResponseEntity<Object> banUser(@PathVariable Integer id) {
        try {
            User sessionUser = SessionUtils.getUser();
            if (sessionUser == null) {
                throw new Exception("User not logged in");
            }
            User user = service.findUserById(sessionUser.getId());
            if (user.getRole() == 0) {
                throw new Exception("Permission denied");
            }
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            User targetUser = service.findUserById(id);
            if (targetUser.getStatus() == 1) {
                return ResponseEntity.ok(service.unbanUser(targetUser));
            } else {
                return ResponseEntity.ok(service.banUser(targetUser));
            }
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }
}
