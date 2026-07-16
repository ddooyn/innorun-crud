package org.practice.springcrudmovie.movie.dto.request;

import lombok.Getter;

@Getter
public class MovieCreateRequest {
    private String title;
    private String description;
    private String imageUrl;
}