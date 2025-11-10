package com.platzi_play.web.controller.controller;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.SuggestRequestDto;
import com.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.domain.service.MovieService;
import com.platzi_play.domain.service.PlatziPlayAiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "Operaciones about movies of platziPlay ")
public class MovieController {

    private final MovieService movieService;

    private final PlatziPlayAiService aiService;

    public MovieController(MovieService movieService, PlatziPlayAiService aiService) {

        this.movieService = movieService;
        this.aiService = aiService;
    }


    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll(){
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @Operation(
            summary = "Get a movie by Id",
            description = "Returns a movie that matches the ID we want to search for",
            responses = {
                    @ApiResponse(responseCode = "200", description = "found movie"),
                    @ApiResponse(responseCode = "404", description = "not found movie",content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findById(@Parameter(description = "movie ID to find", example = "9") @PathVariable long id){
        MovieDto movieDto = this.movieService.findById(id);

        if(movieDto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDto suggestRequestDto) {
        return ResponseEntity.ok(this.aiService.generateMoviesSuggestion(suggestRequestDto.userPreferences()));

    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto){
        return  ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update (@PathVariable long id, @RequestBody UpdateMovieDto updateMovieDto){
        return ResponseEntity.ok(this.movieService.update(id,updateMovieDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable long id){
        this.movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
