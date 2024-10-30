package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dal.FilmGenreRepository;
import ru.yandex.practicum.filmorate.dto.FilmGenreDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FilmGenreMapper;
import ru.yandex.practicum.filmorate.model.FilmGenre;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmGenreService {

    private final FilmGenreRepository filmGenreRepository;

    public FilmGenreService(FilmGenreRepository filmGenreRepository) {
        this.filmGenreRepository = filmGenreRepository;
    }

    public FilmGenreDto createFilmGenre(FilmGenreDto request) {
        if (request.getFilmId() == null) {
            throw new NotFoundException("ID фильма должно быть указано");
        }
        if (request.getGenreId() == null) {
            throw new NotFoundException("ID жанра должно быть указано");
        }

        Optional<FilmGenre> alreadyExistFilmGenre = filmGenreRepository.findByFilmAndGenreId(request.getFilmId(), request.getGenreId());
        if (alreadyExistFilmGenre.isPresent()) {
            throw new NotFoundException("Данный жанр у этого фильма уже существует");
        }
        FilmGenre filmGenre = FilmGenreMapper.mapToFilmGenre(request);
        filmGenre = filmGenreRepository.save(filmGenre);
        return FilmGenreMapper.mapToFilmGenreDto(filmGenre);
    }

    public FilmGenreDto getFilmGenreById(long filmGenreId) {
        return filmGenreRepository.findById(filmGenreId)
                .map(FilmGenreMapper::mapToFilmGenreDto)
                .orElseThrow(() -> new NotFoundException("Жанр фильма не найден с ID: " + filmGenreId));
    }

    public List<FilmGenreDto> getFilmGenreByFilmId(long filmId) {
        return filmGenreRepository.findByFilmId(filmId)
                .stream()
                .map(FilmGenreMapper::mapToFilmGenreDto)
                .collect(Collectors.toList());
    }

    public List<FilmGenreDto> getFilmGenreByGenreId(long genreId) {
        return filmGenreRepository.findByGenreId(genreId)
                .stream()
                .map(FilmGenreMapper::mapToFilmGenreDto)
                .collect(Collectors.toList());
    }

    public List<FilmGenreDto> getFilmGenres() {
        return filmGenreRepository.findAll()
                .stream()
                .map(FilmGenreMapper::mapToFilmGenreDto)
                .collect(Collectors.toList());
    }

    public FilmGenreDto updateFilmGenre(long filmGenreId, FilmGenreDto request) {
        FilmGenre updatedFilmGenre = filmGenreRepository.findById(filmGenreId)
                .map(filmGenre -> FilmGenreMapper.updateFilmGenreFields(filmGenre, request))
                .orElseThrow(() -> new NotFoundException("Жанр не найден"));
        updatedFilmGenre = filmGenreRepository.update(updatedFilmGenre);
        return FilmGenreMapper.mapToFilmGenreDto(updatedFilmGenre);
    }
}
