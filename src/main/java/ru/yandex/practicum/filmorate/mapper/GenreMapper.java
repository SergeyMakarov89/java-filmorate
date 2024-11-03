package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.model.Genre;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenreMapper {

    public Genre mapToGenre(GenreDto request) {
        Genre genre = new Genre();
        genre.setName(request.getName());

        return genre;
    }

    public GenreDto mapToGenreDto(Genre genre) {
        GenreDto dto = new GenreDto();
        dto.setId(genre.getId());
        dto.setName(genre.getName());

        return dto;
    }

    public Genre updateGenreFields(Genre genre, GenreDto request) {
        if (request.getName() != null) {
            genre.setName(request.getName());
        }

        return genre;
    }
}
