package ru.yandex.practicum.filmorate.dto;

import lombok.Data;
import lombok.NonNull;
import ru.yandex.practicum.filmorate.annotation.FilmNameConstraint;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Long duration;
    private List<GenreDto> genres;
    private RatingDto mpa;
}
