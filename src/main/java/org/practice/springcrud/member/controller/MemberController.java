package org.practice.springcrud.member.controller;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.member.dto.MemberCreateRequest;
import org.practice.springcrud.member.dto.MemberCreateResponse;
import org.practice.springcrud.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberCreateResponse> create(
            @RequestBody MemberCreateRequest request
    ) {
        MemberCreateResponse result = memberService.save(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }
}