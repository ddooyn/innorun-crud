package org.practice.springcrudmovie.movie.exception;

import org.practice.springcrudmovie.common.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends ServiceException {
    public MovieNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}