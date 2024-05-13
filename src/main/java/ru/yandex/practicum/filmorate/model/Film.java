package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.annotation.FilmDescriptionConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmNameConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmReleaseDateConstraint;

import java.time.LocalDate;

@Data
public class Film {
    private long id;
    @FilmNameConstraint
    private String name;
    @FilmDescriptionConstraint
    private String description;
    @FilmReleaseDateConstraint
    private LocalDate releaseDate;
    @FilmDurationConstraint
    private Long duration;
}
