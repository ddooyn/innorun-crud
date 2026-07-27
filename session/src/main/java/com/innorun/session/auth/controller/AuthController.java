package com.innorun.session.auth.controller;

import com.innorun.session.auth.dto.LoginRequest;
import com.innorun.session.auth.dto.LoginResponse;
import com.innorun.session.auth.dto.SessionUser;
import com.innorun.session.auth.dto.UserRegisterRequest;
import com.innorun.session.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody UserRegisterRequest request
    ) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpSession session
    ) {
        SessionUser sessionUser = authService.login(request);
        session.setAttribute("sessionUser", sessionUser);

        return ResponseEntity.ok(new LoginResponse(sessionUser.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @SessionAttribute(name = "sessionUser", required = false) SessionUser sessionUser,
            HttpSession session
    ) {
        if (sessionUser == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }

        session.invalidate();
        return ResponseEntity.noContent().build();
    }
}
