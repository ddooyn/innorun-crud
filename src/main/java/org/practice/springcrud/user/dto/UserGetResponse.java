package org.practice.springcrud.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class UserGetResponse {
    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
}