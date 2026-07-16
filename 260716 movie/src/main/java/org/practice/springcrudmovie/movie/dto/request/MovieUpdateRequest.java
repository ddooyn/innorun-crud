package org.practice.springcrudmovie.movie.dto.request;

import lombok.Getter;

@Getter
public class MovieUpdateRequest {
    private String title;
    private String description;
    private String imageUrl;
}