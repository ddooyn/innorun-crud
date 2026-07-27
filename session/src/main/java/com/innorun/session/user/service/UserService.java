package com.innorun.session.user.service;

import com.innorun.session.user.dto.UserGetResponse;
import com.innorun.session.user.entity.User;
import com.innorun.session.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserGetResponse> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream().map((user)
                -> new UserGetResponse(user.getId(), user.getEmail(), user.getName())
        ).toList();
    }
}
