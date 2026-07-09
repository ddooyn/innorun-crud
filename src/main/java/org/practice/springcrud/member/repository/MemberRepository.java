package org.practice.springcrud.member.repository;

import org.practice.springcrud.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}