package org.practice.springcrud.user.controller;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.user.dto.*;
import org.practice.springcrud.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserCreateResponse> create(
            @RequestBody UserCreateRequest request
    ) {
        UserCreateResponse result = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserGetResponse>> getAll() {
        List<UserGetResponse> result = userService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponse> getOne(
            @PathVariable Long userId
    ) {
        UserGetResponse result = userService.getOne(userId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponse> update(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request
    ) {
        UserUpdateResponse result = userService.update(userId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long userId
    ) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}