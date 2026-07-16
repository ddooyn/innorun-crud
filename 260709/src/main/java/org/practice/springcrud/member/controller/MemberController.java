package org.practice.springcrud.member.controller;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.member.dto.*;
import org.practice.springcrud.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberCreateResponse> create(
            @RequestBody MemberCreateRequest request
    ) {
        MemberCreateResponse result = memberService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberGetResponse>> getAll() {
        List<MemberGetResponse> result = memberService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberGetResponse> getOne(
            @PathVariable Long memberId
    ) {
        MemberGetResponse result = memberService.getOne(memberId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<MemberUpdateResponse> update(
            @PathVariable Long memberId,
            @RequestBody MemberUpdateRequest request
    ) {
        MemberUpdateResponse result = memberService.update(memberId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long memberId
    ) {
        memberService.delete(memberId);
        return ResponseEntity.noContent().build();
    }
}