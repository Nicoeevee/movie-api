package com.mycompany.movieapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookmarkException extends RuntimeException {

    public BookmarkException(String message) {
        super(message);
    }
}
