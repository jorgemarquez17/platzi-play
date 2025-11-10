package com.platzi_play.domain.repository;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.UpdateMovieDto;

import java.util.List;

public interface MovieRepository {
    List<MovieDto> getAll();

    MovieDto findById(long id);
    MovieDto save(MovieDto movieDto);

    MovieDto update(long id, UpdateMovieDto updateMovieDto);

    void delete(long id);
}
