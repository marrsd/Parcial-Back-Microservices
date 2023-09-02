package com.dh.movieservice.controller;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.service.MovieService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    private static Logger log = Logger.getLogger(MovieController.class.getName());

    @Value("${idRandom}")
    private String idRandom;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, HttpServletResponse response) {

        log.info("OBTENIENDO MOVIES POR GENERO: " + genre);

        response.addHeader("idrandom", String.valueOf(idRandom));
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {

        log.info("GUARDANDO MOVIE: " + movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }
}
