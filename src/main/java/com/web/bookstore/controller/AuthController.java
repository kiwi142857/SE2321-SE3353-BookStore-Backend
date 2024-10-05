package com.web.bookstore.controller;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.bookstore.dto.LoginOkResponseDTO;
import com.web.bookstore.dto.LoginRequestDTO;
import com.web.bookstore.dto.RegisterRequestDTO;
import com.web.bookstore.dto.ResponseDTO;
import com.web.bookstore.exception.UserBannedException;
import com.web.bookstore.model.Auth;
import com.web.bookstore.model.User;
import com.web.bookstore.service.AuthService;
import com.web.bookstore.service.TimerService;
import com.web.bookstore.util.SessionUtils;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    private final TimerService timerService;

    public AuthController(AuthService service, TimerService timerService) {
        this.service = service;
        this.timerService = timerService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginDTO) {
        try {
            // 更新Cookies
            Auth auth = service.login(loginDTO);
            User user = auth.getUser();
            SessionUtils.setSession(user);
            if (user.getStatus() == 1) {
                throw new UserBannedException("您的账号已被禁用");
            }
            timerService.startTimer();

            return ResponseEntity.ok(new LoginOkResponseDTO(true, "login success"));
        } catch (UserBannedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ResponseDTO(false, e.getMessage()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO registerDTO) {
        try {
            service.register(registerDTO);
            return ResponseEntity.ok(new ResponseDTO(true, "register success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(false, e.getMessage()));
        }
    }

    @PutMapping("/logout")
    public ResponseEntity<Object> logout(HttpSession session) {

        timerService.stopTimer();

        // get the time elapsed
        long timeElapsed = timerService.getElapsedTime();

        // return the time elapsed
        String timeElapsedString = String.format("Time elapsed: %d seconds", timeElapsed / 1000);

        // format the return message
        String message = String.format("Logout success. %s", timeElapsedString);

        session.invalidate();

        return ResponseEntity.ok(new ResponseDTO(true, message));
    }

}
