package org.practice.springcrud.user.repository;

import org.practice.springcrud.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}