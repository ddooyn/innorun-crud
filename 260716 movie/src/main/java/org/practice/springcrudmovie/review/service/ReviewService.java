package org.practice.springcrudmovie.review.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrudmovie.movie.entity.Movie;
import org.practice.springcrudmovie.movie.exception.MovieNotFoundException;
import org.practice.springcrudmovie.movie.repository.MovieRepository;
import org.practice.springcrudmovie.review.dto.request.ReviewCreateRequest;
import org.practice.springcrudmovie.review.dto.request.ReviewUpdateRequest;
import org.practice.springcrudmovie.review.dto.response.ReviewCreateResponse;
import org.practice.springcrudmovie.review.dto.response.ReviewGetResponse;
import org.practice.springcrudmovie.review.dto.response.ReviewUpdateResponse;
import org.practice.springcrudmovie.review.entity.Review;
import org.practice.springcrudmovie.review.exception.ReviewNotFoundException;
import org.practice.springcrudmovie.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public ReviewCreateResponse save(Long movieId, ReviewCreateRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("해당 영화를 찾을 수 없습니다."));

        Review review = new Review(movie, request.getRating(), request.getContent());
        reviewRepository.save(review);

        return new ReviewCreateResponse(
                review.getId(), movie.getId(), review.getRating(), review.getContent(), review.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewGetResponse> getAllByMovieId(Long movieId) {
        List<Review> reviews = reviewRepository.findAllByMovieId(movieId);

        return reviews.stream()
                .map(review -> new ReviewGetResponse(
                        review.getId(), review.getMovie().getId(), review.getRating(), review.getContent(), review.getCreatedAt(), review.getUpdatedAt()
                )).toList();
    }

    @Transactional(readOnly = true)
    public ReviewGetResponse getById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("해당 리뷰를 찾을 수 없습니다."));

        return new ReviewGetResponse(
                review.getId(), review.getMovie().getId(), review.getRating(), review.getContent(), review.getCreatedAt(), review.getUpdatedAt()
        );
    }

    @Transactional
    public ReviewUpdateResponse update(Long reviewId, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("해당 리뷰를 찾을 수 없습니다."));

        review.update(request.getRating(), request.getContent());

        return new ReviewUpdateResponse(
                review.getId(), review.getMovie().getId(), review.getRating(), review.getContent(), review.getCreatedAt(), review.getUpdatedAt()
        );
    }

    @Transactional
    public void delete(Long reviewId) {
        boolean exists = reviewRepository.existsById(reviewId);

        if (!exists) {
            throw new ReviewNotFoundException("해당 리뷰를 찾을 수 없습니다.");
        }

        reviewRepository.deleteById(reviewId);
    }
}