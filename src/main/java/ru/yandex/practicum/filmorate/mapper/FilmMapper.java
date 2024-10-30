package ru.yandex.practicum.filmorate.mapper;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dal.FilmGenreRepository;
import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.dal.RatingRepository;
import ru.yandex.practicum.filmorate.dto.FilmDto;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.dto.RatingDto;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.FilmGenre;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.*;

@Component
public class FilmMapper {


    private final static HashMap<Long, Rating> allRatings = new HashMap<>();
    private final static HashMap<Long, Genre> allGenres = new HashMap<>();
    private final static HashMap<Long, FilmGenre> allFilmGenres = new HashMap<>();

    public FilmMapper(RatingRepository ratingRepository, GenreRepository genreRepository, FilmGenreRepository filmGenreRepository) {
        for (Rating rating : ratingRepository.findAll()) {
            allRatings.put(rating.getId(), rating);
        }
        for (Genre genre : genreRepository.findAll()) {
            allGenres.put(genre.getId(), genre);
        }
        for (FilmGenre filmGenre : filmGenreRepository.findAll()) {
            allFilmGenres.put(filmGenre.getId(), filmGenre);
        }
    }

    public static Film mapToFilm(FilmDto request) {

        Film film = new Film();
        film.setName(request.getName());
        film.setDescription(request.getDescription());
        film.setReleaseDate(request.getReleaseDate());
        film.setDuration(request.getDuration());
        film.setMpa(request.getMpa().getId());
        if (request.getGenres() != null) {
            Set<Long> genres = new TreeSet<>();
            for (GenreDto genreDto : request.getGenres()) {
                genres.add(genreDto.getId());
            }
            film.setGenres(genres);
        }
        return film;
    }

    public static FilmDto mapToFilmDto(Film film) {


        RatingDto ratingDto = RatingMapper.mapToRatingDto(allRatings.get(film.getMpa()));

        FilmDto dto = new FilmDto();
        dto.setId(film.getId());
        dto.setName(film.getName());
        dto.setDescription(film.getDescription());
        dto.setReleaseDate(film.getReleaseDate());
        dto.setDuration(film.getDuration());
        dto.setMpa(ratingDto);

        if (film.getGenres() != null) {
            List<GenreDto> genresDto = new ArrayList<>();
            for (Long genreId : film.getGenres()) {
                for (Genre genre : allGenres.values()) {
                    if (genreId.equals(genre.getId())) {
                        GenreDto genreDto = GenreMapper.mapToGenreDto(genre);
                        genresDto.add(genreDto);
                    }
                }
            }
            dto.setGenres(genresDto);
        }

        return dto;
    }

    public static Film updateFilmFields(Film film, FilmDto request) {

        Set<Long> genres = new TreeSet<>();

        if (request.getName() != null) {
            film.setName(request.getName());
        }
        if (request.getDescription() != null) {
            film.setDescription(request.getDescription());
        }
        if (request.getReleaseDate() != null) {
            film.setReleaseDate(request.getReleaseDate());
        }
        if (request.getDuration() != null) {
            film.setDuration(request.getDuration());
        }
        if (request.getGenres() != null) {
            for (GenreDto genreDto : request.getGenres()) {
                Genre genre = GenreMapper.mapToGenre(genreDto);
                genres.add(genre.getId());
            }
            film.setGenres(genres);
        } else {
            for (FilmGenre filmGenre : allFilmGenres.values()) {
                if (request.getId().equals(filmGenre.getFilmId())) {
                    genres.add(filmGenre.getGenreId());
                }
                film.setGenres(genres);
            }
        }
        if (request.getMpa() != null) {
            film.setMpa(request.getMpa().getId());
        }
        return film;
    }
}
