package org.practice.springcrud.member.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberUpdateResponse {
    private final Long id;
    private final String username;
}