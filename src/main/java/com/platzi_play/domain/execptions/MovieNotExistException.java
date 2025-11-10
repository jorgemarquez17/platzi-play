package com.platzi_play.domain.execptions;

public class MovieNotExistException extends RuntimeException{
    public MovieNotExistException(String titulo){
        super("pelicula "+titulo+" no existe");
    }
}
