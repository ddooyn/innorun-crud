package org.practice.springcrud.memo.repository;

import org.practice.springcrud.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
