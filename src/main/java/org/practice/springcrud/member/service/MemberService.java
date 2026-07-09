package org.practice.springcrud.member.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.member.dto.*;
import org.practice.springcrud.member.entity.Member;
import org.practice.springcrud.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request) {
        Member member = new Member(request.getUsername());
        Member savedMember = memberRepository.save(member);

        return new MemberCreateResponse(
                savedMember.getId(), savedMember.getUsername()
        );
    }

    @Transactional(readOnly = true)
    public List<MemberGetResponse> getAll() {
        List<Member> members = memberRepository.findAll();

        return members.stream()
                .map(member -> new MemberGetResponse(
                        member.getId(), member.getUsername()
                )).toList();
    }

    @Transactional(readOnly = true)
    public MemberGetResponse getOne(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(""));

        return new MemberGetResponse(
                member.getId(), member.getUsername()
        );
    }

    @Transactional
    public MemberUpdateResponse update(Long id, MemberUpdateRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(""));

        member.changeUsername(request.getUsername());

        return new MemberUpdateResponse(
                member.getId(), member.getUsername()
        );
    }
}