package com.example.serieservice.controller;

import com.example.serieservice.model.Serie;
import com.example.serieservice.queue.SerieSender;
import com.example.serieservice.service.SerieService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author vaninagodoy
 */

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
public class SerieController {

    private final SerieService serieService;
    
    private final SerieSender serieSender;

    private static java.util.logging.Logger log = Logger.getLogger(SerieController.class.getName());

    @GetMapping
    public List<Serie> getAll() {
        return serieService.getAll();
    }

    @GetMapping("/{genre}")
    public List<Serie> getSerieByGenre(@PathVariable String genre) {
        log.info("Listando series de género: " + genre);
        return serieService.getSeriesBygGenre(genre);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Serie serie) {
        serieSender.send(serie);
        serieService.create(serie);
        log.info("Guardando serie " + serie.getName() + "con id: " + serie.getId());
        return serie.getId();
    }
}
