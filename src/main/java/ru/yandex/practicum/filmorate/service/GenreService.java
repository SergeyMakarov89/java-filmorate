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
    private final GenreMapper genreMapper;

    public GenreService(GenreMapper genreMapper, GenreRepository genreRepository) {

        this.genreMapper = genreMapper;
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
        Genre genre = genreMapper.mapToGenre(request);
        genre = genreRepository.save(genre);
        return genreMapper.mapToGenreDto(genre);
    }

    public GenreDto getGenreById(long genreId) {
        return genreRepository.findById(genreId)
                .map(genreMapper::mapToGenreDto)
                .orElseThrow(() -> new NotFoundException("Жанр не найден с ID: " + genreId));
    }

    public List<GenreDto> getGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::mapToGenreDto)
                .collect(Collectors.toList());
    }

    public GenreDto updateGenre(long genreId, GenreDto request) {
        Genre updatedGenre = genreRepository.findById(genreId)
                .map(genre -> genreMapper.updateGenreFields(genre, request))
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
        updatedGenre = genreRepository.update(updatedGenre);
        return genreMapper.mapToGenreDto(updatedGenre);
    }
}
