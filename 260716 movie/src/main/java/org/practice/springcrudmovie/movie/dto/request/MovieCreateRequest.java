package org.practice.springcrudmovie.movie.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class MovieCreateRequest {
    @NotBlank(message = "영화 제목을 한 글자 이상 입력해주세요.")
    private String title;
    private String description;

    @NotBlank(message = "영화 이미지 링크를 입력해주세요.")
    @URL(message = "영화 이미지 링크 형식을 올바르게 입력해주세요.")
    private String imageUrl;
}