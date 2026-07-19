package org.practice.springcrudmovie.review.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.practice.springcrudmovie.movie.entity.Movie;
import org.practice.springcrudmovie.movie.repository.MovieRepository;
import org.practice.springcrudmovie.review.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("movieId로 review 조회에 성공한다.")
    void findAllByMovieId() {
        // given
        Movie movie1 = new Movie("해리 포터와 마법사의 돌", "설명1", "https://www.test.abc/test1.png");
        Movie movie2 = new Movie("해리 포터와 비밀의 방", "설명2", "https://www.test.abc/test2.png");

        movieRepository.save(movie1);
        movieRepository.save(movie2);

        Review review1 = new Review(movie1, 5.0, "재밌어요");
        Review review2 = new Review(movie1, 0.0, "");
        Review review3 = new Review(movie2, 3.5, "볼만해요");

        reviewRepository.saveAll(List.of(review1, review2, review3));

        List<Movie> movies = movieRepository.findAll();
        movies.forEach(movie ->
                log.info("movie title={}", movie.getTitle())
        );

        // when
        List<Review> result = reviewRepository.findAllByMovieId(movie1.getId());

        // then
        assertThat(result).hasSize(2);
        assertThat(result)
                .extracting(Review::getMovie)
                .containsOnly(movie1);
    }
}