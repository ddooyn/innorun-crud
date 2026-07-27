package com.innorun.session.auth.dto;

import lombok.Getter;

@Getter
public class SessionUser {

    private final Long id;
    private final String email;

    public SessionUser(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
