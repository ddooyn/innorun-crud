package org.practice.springcrud.user.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.user.dto.*;
import org.practice.springcrud.user.entity.User;
import org.practice.springcrud.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponse save(UserCreateRequest request) {
        User user = new User(request.getName());
        userRepository.save(user);

        return new UserCreateResponse(
                user.getId(), user.getName()
        );
    }

    @Transactional(readOnly = true)
    public List<UserGetResponse> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserGetResponse(
                        user.getId(), user.getName()
                )).toList();
    }

    @Transactional(readOnly = true)
    public UserGetResponse getOne(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(""));

        return new UserGetResponse(
                user.getId(), user.getName()
        );
    }

    @Transactional
    public UserUpdateResponse update(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(""));

        user.changeName(request.getName());

        return new UserUpdateResponse(
                user.getId(), user.getName()
        );
    }

    @Transactional
    public void delete(Long userId) {
        boolean exists = userRepository.existsById(userId);

        if (!exists) {
            throw new IllegalStateException("");
        }

        userRepository.deleteById(userId);
    }
}
