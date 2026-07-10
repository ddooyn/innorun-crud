package org.practice.springcrud.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGetResponse {
    private final Long id;
    private final String name;
}