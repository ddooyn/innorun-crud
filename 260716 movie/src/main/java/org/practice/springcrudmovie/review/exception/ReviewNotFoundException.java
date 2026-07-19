package org.practice.springcrudmovie.review.exception;

import org.practice.springcrudmovie.common.exception.ServiceException;
import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends ServiceException {
    public ReviewNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}