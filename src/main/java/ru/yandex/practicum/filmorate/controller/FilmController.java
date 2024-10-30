package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FilmDto createFilm(@RequestBody FilmDto filmRequest) {
        return filmService.createFilm(filmRequest);
    }

    @PutMapping("/{filmId}")
    public FilmDto updateFilm(@PathVariable("filmId") long filmId, @RequestBody FilmDto request) {
        return filmService.updateFilm(filmId, request);
    }

    @PutMapping
    public FilmDto updateFilmFull(@RequestBody FilmDto request) {
        return filmService.updateFilmFull(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FilmDto> getFilms() {
        return filmService.getFilms();
    }

    @GetMapping("/{filmId}")
    @ResponseStatus(HttpStatus.OK)
    public FilmDto getFilmById(@PathVariable("filmId") long filmId) {
        return filmService.getFilmById(filmId);
    }

    @GetMapping("/{filmId}/likes")
    @ResponseStatus(HttpStatus.OK)
    public long getFilmLikes(@PathVariable("filmId") long filmId) {
        return filmService.getFilmLikes(filmId);
    }

    @PutMapping("/{filmId}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public long addLikeToFilm(@PathVariable("filmId") long filmId, @PathVariable("userId") long userId) {
        return filmService.addLikeToFilm(filmId, userId);
    }

    @DeleteMapping("/{filmId}/like/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteLikeFromFilm(@PathVariable("filmId") long filmId, @PathVariable("userId") long userId) {
        return filmService.deleteLikeFromFilm(filmId, userId);
    }

    @GetMapping("/popular")
    public List<FilmDto> getPopularFilms(@RequestParam(defaultValue = "10", required = false) Integer count) {
        return filmService.getPopularFilms(count);
    }
}
