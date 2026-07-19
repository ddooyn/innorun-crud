package org.practice.springcrudmovie.review.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ReviewCreateResponse {
    private final Long id;
    private final Long movieId;
    private final Double rating;
    private final String content;
    private final LocalDateTime createdAt;
}