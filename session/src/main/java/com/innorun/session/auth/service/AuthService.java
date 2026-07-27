package com.innorun.session.auth.service;

import com.innorun.session.auth.dto.LoginRequest;
import com.innorun.session.auth.dto.SessionUser;
import com.innorun.session.auth.dto.UserRegisterRequest;
import com.innorun.session.common.PasswordEncoder;
import com.innorun.session.user.entity.User;
import com.innorun.session.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(UserRegisterRequest request) {
        boolean exists = userRepository.existsByEmail(request.getEmail());
        if (exists) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), encodedPassword, request.getName());
        userRepository.save(user);
    }

    @Transactional
    public SessionUser login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 이메일입니다."));

        String encodedPassword = user.getPassword();
        String inputPassword = request.getPassword();

        if (!passwordEncoder.matches(inputPassword, encodedPassword)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return new SessionUser(user.getId(), user.getEmail());
    }
}
