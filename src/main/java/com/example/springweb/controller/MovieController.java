package com.example.springweb.controller;

import com.example.springweb.entity.Movie;
import com.example.springweb.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/update")
    public void patchMovie(@RequestParam Long id, @RequestParam Long score) {
        movieService.update(id, score);
    }

    @GetMapping
    public ResponseEntity<Optional<Movie>> getMovie(@RequestParam Long id) {
        if(movieService.getById(id).isPresent()) {
            return new ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(movieService.getById(id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public void deleteMovie(@RequestParam Long id) {
        movieService.delete(id);
    }
}
