package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.film.InMemoryFilmStorage;

import java.util.Collection;

@Service
public class FilmService {
    private final InMemoryFilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    public Collection<Film> findAll() {
        return inMemoryFilmStorage.findAll();
    }

    public Film findFilmById(Long filmId) {
        return inMemoryFilmStorage.findFilmById(filmId);
    }

    public Film create(Film film) {
        return inMemoryFilmStorage.create(film);
    }

    public Film update(Film newFilm) {
        return inMemoryFilmStorage.update(newFilm);
    }

    public Film addLikeToFilm(Long filmId, Long userId) {
        return inMemoryFilmStorage.addLikeToFilm(filmId, userId);
    }

    public Film deleteLikeFromFilm(Long filmId, Long userId) {
        return inMemoryFilmStorage.deleteLikeFromFilm(filmId, userId);
    }

    public Collection<Film> getPopularFilms(Long count) {
        return inMemoryFilmStorage.getPopularFilms(count);
    }
}
