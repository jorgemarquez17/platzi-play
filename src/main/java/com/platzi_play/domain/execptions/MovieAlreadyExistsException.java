package com.platzi_play.domain.execptions;

public class MovieAlreadyExistsException extends  RuntimeException{

    public MovieAlreadyExistsException(String movieTitle){
        super("La pelicula " +movieTitle+" ya existe.");
    }
}
