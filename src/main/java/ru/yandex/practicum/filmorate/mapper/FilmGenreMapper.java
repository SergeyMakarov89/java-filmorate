package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.FilmGenreDto;
import ru.yandex.practicum.filmorate.model.FilmGenre;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FilmGenreMapper {

    public FilmGenre mapToFilmGenre(FilmGenreDto request) {
        FilmGenre filmGenre = new FilmGenre();
        filmGenre.setFilmId(request.getFilmId());
        filmGenre.setGenreId(request.getGenreId());

        return filmGenre;
    }

    public FilmGenreDto mapToFilmGenreDto(FilmGenre filmGenre) {
        FilmGenreDto dto = new FilmGenreDto();
        dto.setId(filmGenre.getId());
        dto.setFilmId(filmGenre.getFilmId());
        dto.setGenreId(filmGenre.getGenreId());

        return dto;
    }

    public FilmGenre updateFilmGenreFields(FilmGenre filmGenre, FilmGenreDto request) {
        if (request.getFilmId() != null) {
            filmGenre.setFilmId(request.getFilmId());
        }
        if (request.getGenreId() != null) {
            filmGenre.setGenreId(request.getGenreId());
        }

        return filmGenre;
    }
}
