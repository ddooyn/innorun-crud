package com.innorun.session.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRegisterRequest {

    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
}
