package org.practice.springcrud.member.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrud.member.dto.*;
import org.practice.springcrud.member.entity.Member;
import org.practice.springcrud.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request) {
        Member member = new Member(request.getUsername());
        memberRepository.save(member);

        return new MemberCreateResponse(
                member.getId(), member.getUsername()
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
    public MemberGetResponse getOne(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException(""));

        return new MemberGetResponse(
                member.getId(), member.getUsername()
        );
    }

    @Transactional
    public MemberUpdateResponse update(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException(""));

        String memberUsername = member.getUsername();
        String requestUsername = request.getUsername();

        if (ObjectUtils.nullSafeEquals(memberUsername, requestUsername)) {
            throw new IllegalArgumentException("");
        }

        member.changeUsername(request.getUsername());
        memberRepository.saveAndFlush(member);

        return new MemberUpdateResponse(
                member.getId(), member.getUsername()
        );
    }

    @Transactional
    public void delete(Long memberId) {
        boolean exists = memberRepository.existsById(memberId);

        if (!exists) {
            throw new IllegalStateException("");
        }

        memberRepository.deleteById(memberId);
    }
}