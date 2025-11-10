package com.platzi_play.web.controller.exception;

import com.platzi_play.domain.execptions.MovieAlreadyExistsException;
import com.platzi_play.domain.execptions.MovieNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RespExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handlerException(MovieAlreadyExistsException ex){
        Error error = new Error("movie-already-exists",ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MovieNotExistException.class)
    public ResponseEntity<Error> handlerExceptionNotExist(MovieNotExistException ex){
        Error error = new Error("movie-not_exists",ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
