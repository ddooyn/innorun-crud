package org.practice.springcrudmovie.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.practice.springcrudmovie.review.dto.request.ReviewCreateRequest;
import org.practice.springcrudmovie.review.dto.request.ReviewUpdateRequest;
import org.practice.springcrudmovie.review.dto.response.ReviewCreateResponse;
import org.practice.springcrudmovie.review.dto.response.ReviewGetResponse;
import org.practice.springcrudmovie.review.dto.response.ReviewUpdateResponse;
import org.practice.springcrudmovie.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewCreateResponse> create(
            @PathVariable Long movieId,
            @Valid @RequestBody ReviewCreateRequest request
    ) {
        ReviewCreateResponse result = reviewService.save(movieId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewGetResponse>> getAllByMovieId(
            @PathVariable Long movieId
    ) {
        List<ReviewGetResponse> result = reviewService.getAllByMovieId(movieId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewGetResponse> getOne(
            @PathVariable Long reviewId
    ) {
        ReviewGetResponse result = reviewService.getById(reviewId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewUpdateResponse> update(
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewUpdateRequest request
    ) {
        ReviewUpdateResponse result = reviewService.update(reviewId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long reviewId
    ) {
        reviewService.delete(reviewId);
        return ResponseEntity.noContent().build();
    }
}
