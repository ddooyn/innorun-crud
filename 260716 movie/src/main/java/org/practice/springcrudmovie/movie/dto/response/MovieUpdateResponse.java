package org.practice.springcrudmovie.movie.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MovieUpdateResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final String imageUrl;
}