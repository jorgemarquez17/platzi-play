package com.platzi_play.persistence.mapper;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "duracion", target = "duration")
    @Mapping(source = "genero", target = "genre",qualifiedByName = "stringToGenre")
    @Mapping(source = "fechaEstreno", target = "releaseDate")
    @Mapping(source = "clasificacion", target = "rating")
    @Mapping(source = "estado", target = "state")

    MovieDto toDto(MovieEntity entity);
    @InheritInverseConfiguration
    @Mapping(source = "genre",target = "genero",qualifiedByName = "genreToString")
    MovieEntity toEntity(MovieDto dto);

    List<MovieDto> toDto(Iterable<MovieEntity> entities);
}
