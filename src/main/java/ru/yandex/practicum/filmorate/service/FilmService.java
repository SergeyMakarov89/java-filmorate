package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.*;

import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.mapper.FilmMapper;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.model.Like;

import java.time.LocalDate;
import java.util.*;

@Service
public class FilmService {
    private final FilmRepository filmRepository;
    private final LikeRepository likeRepository;
    private final FilmGenreRepository filmGenreRepository;
    private final GenreRepository genreRepository;
    private final RatingRepository ratingRepository;

    public FilmService(FilmRepository filmRepository, LikeRepository likeRepository, FilmGenreRepository filmGenreRepository, GenreRepository genreRepository, RatingRepository ratingRepository) {
        this.filmRepository = filmRepository;
        this.likeRepository = likeRepository;
        this.filmGenreRepository = filmGenreRepository;
        this.genreRepository = genreRepository;
        this.ratingRepository = ratingRepository;
    }

    public FilmDto createFilm(FilmDto request) {

        validFilmDto(request);
        Film film = FilmMapper.mapToFilm(request);
        film = filmRepository.save(film);
        if (request.getGenres() != null) {
            for (GenreDto genreDto : request.getGenres()) {
                FilmGenre filmGenre = new FilmGenre();
                filmGenre.setFilmId(film.getId());
                filmGenre.setGenreId(genreDto.getId());
                filmGenreRepository.save(filmGenre);
            }
        }
        return FilmMapper.mapToFilmDto(film);
    }

    public FilmDto getFilmById(long filmId) {
        Film film = filmRepository.findById(filmId).get();
        for (FilmGenre filmGenre : filmGenreRepository.findByFilmId(film.getId())) {
            if (film.getGenres() == null) {
                Set<Long> genres = new HashSet<>();
                genres.add(filmGenre.getGenreId());
                film.setGenres(genres);
            } else {
                Set<Long> genres = film.getGenres();
                genres.add(filmGenre.getGenreId());
                film.setGenres(genres);
            }
        }
        FilmDto filmDto = FilmMapper.mapToFilmDto(film);

        return filmDto;
    }

    public List<FilmDto> getFilms() {
        List<FilmDto> filmDtoList = new ArrayList<>();
        List<Film> filmList = filmRepository.findAll();
        for (Film film : filmList) {
            for (FilmGenre filmGenre : filmGenreRepository.findByFilmId(film.getId())) {
                if (film.getGenres() == null) {
                    Set<Long> genres = new HashSet<>();
                    genres.add(filmGenre.getGenreId());
                    film.setGenres(genres);
                } else {
                    Set<Long> genres = film.getGenres();
                    genres.add(filmGenre.getGenreId());
                    film.setGenres(genres);
                }
            }
            FilmDto filmDto = FilmMapper.mapToFilmDto(film);
            filmDtoList.add(filmDto);
        }
        return filmDtoList;
    }

    public FilmDto updateFilm(long filmId, FilmDto request) {
        Film updatedFilm = filmRepository.findById(filmId)
                .map(film -> FilmMapper.updateFilmFields(film, request))
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        updatedFilm = filmRepository.update(updatedFilm);
        return FilmMapper.mapToFilmDto(updatedFilm);
    }

    public FilmDto updateFilmFull(FilmDto request) {
        if (request.getId() == null) {
            throw new ValidationException("ID фильма должно быть указано");
        }
        Film updatedFilm = filmRepository.findById(request.getId())
                .map(film -> FilmMapper.updateFilmFields(film, request))
                .orElseThrow(() -> new NotFoundException("Фильм не найден"));
        updatedFilm = filmRepository.update(updatedFilm);
        return FilmMapper.mapToFilmDto(updatedFilm);
    }

    public long getFilmLikes(long filmId) {
        return likeRepository.findLikesByFilmId(filmId);
    }

    public long addLikeToFilm(long filmId, long userId) {
        Optional<Like> alreadyExistLike = likeRepository.findByUserLike(filmId, userId);
        if (alreadyExistLike.isPresent()) {
            throw new NotFoundException("Лайк этого пользователя уже есть у этого фильма");
        }
        return likeRepository.saveLike(filmId, userId);
    }

    public boolean deleteLikeFromFilm(long filmId, long userId) {
        Optional<Like> alreadyExistLike = likeRepository.findByUserLike(filmId, userId);
        if (alreadyExistLike.isPresent()) {
            return likeRepository.deleteLike(filmId, userId);
        } else {
            System.out.println("Такого лайка у фильма не существует");
            return false;
        }
    }

    public List<FilmDto> getPopularFilms(Integer count) {

        List<FilmDto> filmDtoList = new ArrayList<>();
        List<Film> filmList = filmRepository.getPopularFilms(count);
        for (Film film : filmList) {
            for (FilmGenre filmGenre : filmGenreRepository.findByFilmId(film.getId())) {
                if (film.getGenres() == null) {
                    Set<Long> genres = new HashSet<>();
                    genres.add(filmGenre.getGenreId());
                    film.setGenres(genres);
                } else {
                    Set<Long> genres = film.getGenres();
                    genres.add(filmGenre.getGenreId());
                    film.setGenres(genres);
                }
            }
            FilmDto filmDto = FilmMapper.mapToFilmDto(film);
            filmDtoList.add(filmDto);
        }
        return filmDtoList;
    }

    public void validFilmDto(FilmDto request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Название фильма должно быть указано");
        }
        if (request.getDescription().length() > 200) {
            throw new ValidationException("Описание фильма больше 200 символов");
        }
        if (request.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("Дата выхода фильма не может быть раньше 28 декабря 1895 года");
        }
        if (request.getDuration() <= 0) {
            throw new ValidationException("Длительность не может быть равно 0 или меньше 0");
        }
        if (ratingRepository.findById(request.getMpa().getId()).isEmpty()) {
            throw new ValidationException("Такой возрастной рейтинг отсутствует");
        }
        if (request.getGenres() != null) {
            for (GenreDto genre : request.getGenres()) {
                if (genreRepository.findById(genre.getId()).isEmpty()) {
                    throw new ValidationException("Такого жанра не существует, добавьте жанр");
                }
            }
        }
    }
}
