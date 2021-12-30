package com.example.springweb.service;

import com.example.springweb.entity.Movie;
import com.example.springweb.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void update(Long id, Long score) {
        Optional<Movie> movie = movieRepository.findById(id);
        movie.ifPresent(m -> {
            m.setScore(score);
            movieRepository.save(m);
        });
    }

    public Optional<Movie> getById(Long id){
        return movieRepository.findById(id);
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        movieRepository.save(new Movie(1L, "Piła", 3L));
        movieRepository.save(new Movie(2L, "Sarnie zniwo", 2L));
    }
}
