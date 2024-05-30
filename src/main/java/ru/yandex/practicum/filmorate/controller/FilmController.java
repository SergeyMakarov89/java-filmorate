package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public Collection<Film> findAll() {
        return filmService.findAll();
    }

    @GetMapping("/{filmId}")
    public Film findFilmById(@PathVariable Long filmId) {
        return filmService.findFilmById(filmId);
    }

    @PostMapping
    public Film create(@Valid @RequestBody(required = true) Film film) {
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@Valid @RequestBody(required = true) Film newFilm) {
        return filmService.update(newFilm);
    }

    @PutMapping("/{filmId}/like/{userId}")
    public Film addLikeToFilm(@PathVariable Long filmId,
                              @PathVariable Long userId) {
        return filmService.addLikeToFilm(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    public Film deleteLikeFromFilm(@PathVariable Long filmId,
                              @PathVariable Long userId) {
        return filmService.deleteLikeFromFilm(filmId, userId);
    }

    @GetMapping("/popular")
    public Collection<Film> getPopularFilms(@RequestParam(defaultValue = "10", required = false) Long count) {
        return filmService.getPopularFilms(count);
    }
}
