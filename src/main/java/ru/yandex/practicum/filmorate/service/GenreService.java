package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.GenreMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreDto createGenre(GenreDto request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new NotFoundException("Название жанра должно быть указано");
        }

        Optional<Genre> alreadyExistGenre = genreRepository.findByName(request.getName());
        if (alreadyExistGenre.isPresent()) {
            throw new NotFoundException("Данное название уже используется");
        }
        Genre genre = GenreMapper.mapToGenre(request);
        genre = genreRepository.save(genre);
        return GenreMapper.mapToGenreDto(genre);
    }

    public GenreDto getGenreById(long genreId) {
        return genreRepository.findById(genreId)
                .map(GenreMapper::mapToGenreDto)
                .orElseThrow(() -> new NotFoundException("Жанр не найден с ID: " + genreId));
    }

    public List<GenreDto> getGenres() {
        return genreRepository.findAll()
                .stream()
                .map(GenreMapper::mapToGenreDto)
                .collect(Collectors.toList());
    }

    public GenreDto updateGenre(long genreId, GenreDto request) {
        Genre updatedGenre = genreRepository.findById(genreId)
                .map(genre -> GenreMapper.updateGenreFields(genre, request))
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
        updatedGenre = genreRepository.update(updatedGenre);
        return GenreMapper.mapToGenreDto(updatedGenre);
    }
}
