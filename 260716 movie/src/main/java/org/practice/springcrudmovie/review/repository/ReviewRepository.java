package org.practice.springcrudmovie.review.repository;

import org.practice.springcrudmovie.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovieId(Long movieId);
}