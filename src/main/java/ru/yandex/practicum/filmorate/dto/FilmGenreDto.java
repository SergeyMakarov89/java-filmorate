package ru.yandex.practicum.filmorate.dto;

import lombok.Data;

@Data
public class FilmGenreDto {
    private Long id;
    private Long filmId;
    private Long genreId;
}
