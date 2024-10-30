package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.annotation.FilmDescriptionConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmDurationConstraint;
import ru.yandex.practicum.filmorate.annotation.FilmReleaseDateConstraint;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
public class Film {
    private Long id;
    private String name;
    @FilmDescriptionConstraint
    private String description;
    @FilmReleaseDateConstraint
    private LocalDate releaseDate;
    @FilmDurationConstraint
    private Long duration;
    private Set<Long> genres;
    private Long mpa;
}
