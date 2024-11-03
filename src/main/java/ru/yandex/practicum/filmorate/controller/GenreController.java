package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.service.GenreService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenreDto createGenre(@RequestBody GenreDto genreRequest) {
        return genreService.createGenre(genreRequest);
    }

    @PutMapping("/{genreId}")
    public GenreDto updateGenre(@PathVariable("genreId") long genreId, @RequestBody GenreDto request) {
        return genreService.updateGenre(genreId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreDto> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{genreId}")
    @ResponseStatus(HttpStatus.OK)
    public GenreDto getGenreById(@PathVariable("genreId") long genreId) {
        return genreService.getGenreById(genreId);
    }
}
