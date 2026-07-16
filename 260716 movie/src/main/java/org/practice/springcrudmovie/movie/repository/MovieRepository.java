package org.practice.springcrudmovie.movie.repository;

import org.practice.springcrudmovie.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
