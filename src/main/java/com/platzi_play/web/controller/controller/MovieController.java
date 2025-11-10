package com.platzi_play.web.controller.controller;

import com.platzi_play.domain.dto.MovieDto;
import com.platzi_play.domain.dto.SuggestRequestDto;
import com.platzi_play.domain.dto.UpdateMovieDto;
import com.platzi_play.domain.service.MovieService;
import com.platzi_play.domain.service.PlatziPlayAiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
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

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable long id){
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
