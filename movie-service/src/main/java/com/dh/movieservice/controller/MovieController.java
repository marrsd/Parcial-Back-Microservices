package com.dh.movieservice.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dh.movieservice.model.Movie;
import com.dh.movieservice.queue.MovieSender;
import com.dh.movieservice.service.MovieService;

import lombok.RequiredArgsConstructor;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    
    private final MovieSender sender;

    private static Logger log = Logger.getLogger(MovieController.class.getName());

    @Value("${idRandom}")
    private String idRandom;

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, HttpServletResponse response) {

        log.info("OBTENIENDO MOVIES POR GENERO: " + genre);

        response.addHeader("idrandom", String.valueOf(idRandom));
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {

        log.info("GUARDANDO MOVIE: " + movie);
        sender.send(movie);
        return ResponseEntity.ok().body(movieService.save(movie));
    }
}
