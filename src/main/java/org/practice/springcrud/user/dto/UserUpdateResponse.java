package org.practice.springcrud.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserUpdateResponse {
    private final Long id;
    private final String name;
}