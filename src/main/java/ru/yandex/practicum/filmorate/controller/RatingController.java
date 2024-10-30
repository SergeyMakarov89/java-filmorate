package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.RatingDto;
import ru.yandex.practicum.filmorate.service.RatingService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/mpa")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RatingDto createGenre(@RequestBody RatingDto ratingRequest) {
        return ratingService.createRating(ratingRequest);
    }

    @PutMapping("/{ratingId}")
    public RatingDto updateRating(@PathVariable("ratingId") long ratingId, @RequestBody RatingDto request) {
        return ratingService.updateRating(ratingId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RatingDto> getRatings() {
        return ratingService.getRatings();
    }

    @GetMapping("/{ratingId}")
    @ResponseStatus(HttpStatus.OK)
    public RatingDto getRatingById(@PathVariable("ratingId") long ratingId) {
        return ratingService.getRatingById(ratingId);
    }
}
