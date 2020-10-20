package com.mycompany.movieapi.mapper;

import com.mycompany.movieapi.model.Movie;
import com.mycompany.movieapi.rest.dto.CreateMovieRequest;
import com.mycompany.movieapi.rest.dto.MovieDto;
import com.mycompany.movieapi.service.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = MovieService.class
)
public interface MovieMapper {

    Movie toMovie(CreateMovieRequest createMovieRequest);

    MovieDto toMovieDto(Movie movie);

}