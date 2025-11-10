package com.platzi_play.persistence;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.domain.execptions.MovieAlreadyExistsException;
import com.platzi_play.domain.execptions.MovieNotExistException;
import com.platzi_play.domain.repository.MovieRepository;
import com.platzi_play.persistence.crud.CrudMovieEntity;
import com.platzi_play.persistence.entity.MovieEntity;
import com.platzi_play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository

public class MovieEntityRepository implements MovieRepository {

    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity,MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDto> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDto findById(long id) {
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
       return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDto save(MovieDto movieDto) {

        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null)
        {
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public MovieDto update(long id, UpdateMovieDto updateMovieDto) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        if (movieEntity == null)
        {
            throw new MovieNotExistException(updateMovieDto.title());
        }

        movieEntity.setTitulo(updateMovieDto.title());
        movieEntity.setFechaEstreno(updateMovieDto.releaseDate());
        movieEntity.setClasificacion(BigDecimal.valueOf(updateMovieDto.rating()));

        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public void delete(long id) {
//        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
//        if (movieEntity != null) {
//            this.crudMovieEntity.delete(movieEntity);
//        }
        this.crudMovieEntity.deleteById(id);
    }
}
