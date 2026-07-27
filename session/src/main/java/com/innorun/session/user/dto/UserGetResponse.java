package com.innorun.session.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserGetResponse {
    final Long id;
    final String email;
    final String name;
}
