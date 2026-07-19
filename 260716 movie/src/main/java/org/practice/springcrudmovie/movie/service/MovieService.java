package org.practice.springcrudmovie.movie.service;

import lombok.RequiredArgsConstructor;
import org.practice.springcrudmovie.movie.dto.request.MovieCreateRequest;
import org.practice.springcrudmovie.movie.dto.request.MovieUpdateRequest;
import org.practice.springcrudmovie.movie.dto.response.MovieCreateResponse;
import org.practice.springcrudmovie.movie.dto.response.MovieGetResponse;
import org.practice.springcrudmovie.movie.dto.response.MovieUpdateResponse;
import org.practice.springcrudmovie.movie.entity.Movie;
import org.practice.springcrudmovie.movie.exception.MovieNotFoundException;
import org.practice.springcrudmovie.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    @Transactional
    public MovieCreateResponse save(MovieCreateRequest request) {
        Movie movie = new Movie(request.getTitle(), request.getDescription(), request.getImageUrl());
        movieRepository.save(movie);

        return new MovieCreateResponse(
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getImageUrl()
        );
    }

    @Transactional(readOnly = true)
    public List<MovieGetResponse> getAll() {
        List<Movie> movies = movieRepository.findAll();

        return movies.stream()
                .map(movie -> new MovieGetResponse(
                        movie.getId(), movie.getTitle(), movie.getDescription(), movie.getImageUrl()
                )).toList();
    }

    @Transactional(readOnly = true)
    public MovieGetResponse getOne(Long movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("해당 영화를 찾을 수 없습니다."));

        return new MovieGetResponse(
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getImageUrl()
        );
    }

    @Transactional
    public MovieUpdateResponse update(Long movieId, MovieUpdateRequest request) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("해당 영화를 찾을 수 없습니다."));

        movie.update(request.getTitle(), request.getDescription(), request.getImageUrl());

        return new MovieUpdateResponse(
                movie.getId(), movie.getTitle(), movie.getDescription(), movie.getImageUrl()
        );
    }

    @Transactional
    public void delete(Long movieId) {
        boolean exists = movieRepository.existsById(movieId);

        if (!exists) {
            throw new MovieNotFoundException("해당 영화를 찾을 수 없습니다.");
        }

        movieRepository.deleteById(movieId);
    }
}