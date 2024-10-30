package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmGenreDto;
import ru.yandex.practicum.filmorate.service.FilmGenreService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/filmgenres")
public class FilmGenreController {

    private final FilmGenreService filmGenreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmGenreDto createFilmGenre(@RequestBody FilmGenreDto filmGenreRequest) {
        return filmGenreService.createFilmGenre(filmGenreRequest);
    }

    @PutMapping("/{filmGenreId}")
    public FilmGenreDto updateFilmGenre(@PathVariable("filmGenreId") long filmGenreId, @RequestBody FilmGenreDto request) {
        return filmGenreService.updateFilmGenre(filmGenreId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FilmGenreDto> getFilmGenres() {
        return filmGenreService.getFilmGenres();
    }

    @GetMapping("/{filmGenreId}")
    @ResponseStatus(HttpStatus.OK)
    public FilmGenreDto getFilmGenreById(@PathVariable("filmGenreId") long filmGenreId) {
        return filmGenreService.getFilmGenreById(filmGenreId);
    }
}
