package ru.yandex.practicum.filmorate.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.yandex.practicum.filmorate.annotation.FilmDescriptionConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmGenresConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmMpaConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmReleaseDateConstraint;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {
    private Long id;
    @NotBlank
    private String name;
    @FilmDescriptionConstraint
    private String description;
    @FilmReleaseDateConstraint
    private LocalDate releaseDate;
    @Positive
    private Long duration;
    @FilmGenresConstraint
    private List<GenreDto> genres;
    @FilmMpaConstraint
    private RatingDto mpa;
}
