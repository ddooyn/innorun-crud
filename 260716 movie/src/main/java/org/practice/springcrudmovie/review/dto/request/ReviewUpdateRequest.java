package org.practice.springcrudmovie.review.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewUpdateRequest {
    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    @Size(max = 1000)
    private String content;
}