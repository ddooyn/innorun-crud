package org.practice.springcrudmovie.movie.controller;

import lombok.RequiredArgsConstructor;
import org.practice.springcrudmovie.movie.dto.request.MovieCreateRequest;
import org.practice.springcrudmovie.movie.dto.request.MovieUpdateRequest;
import org.practice.springcrudmovie.movie.dto.response.MovieCreateResponse;
import org.practice.springcrudmovie.movie.dto.response.MovieGetResponse;
import org.practice.springcrudmovie.movie.dto.response.MovieUpdateResponse;
import org.practice.springcrudmovie.movie.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieCreateResponse> create(
            @RequestBody MovieCreateRequest request
    ) {
        MovieCreateResponse result = movieService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<List<MovieGetResponse>> getAll() {
        List<MovieGetResponse> result = movieService.getAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieGetResponse> getOne(
            @PathVariable Long movieId
    ) {
        MovieGetResponse result = movieService.getOne(movieId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieUpdateResponse> update(
            @PathVariable Long movieId,
            @RequestBody MovieUpdateRequest request
    ) {
        MovieUpdateResponse result = movieService.update(movieId, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long movieId
    ) {
        movieService.delete(movieId);
        return ResponseEntity.noContent().build();
    }
}
