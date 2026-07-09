package org.practice.springcrud.member.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.member.dto.MemberCreateRequest;
import org.practice.springcrud.member.dto.MemberCreateResponse;
import org.practice.springcrud.member.entity.Member;
import org.practice.springcrud.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request) {
        Member member = new Member(request.getUsername());
        Member savedMember = memberRepository.save(member);

        return new MemberCreateResponse(
                savedMember.getId(),
                savedMember.getUsername()
        );
    }
}