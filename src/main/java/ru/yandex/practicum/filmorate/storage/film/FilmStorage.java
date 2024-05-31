package ru.yandex.practicum.filmorate.storage.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    public Collection<Film> findAll();

    public Film findFilmById(Long filmId);

    public Film create(Film film);

    public Film update(Film newFilm);

    public Film addLikeToFilm(Long filmId, Long userId);

    public Film deleteLikeFromFilm(Long filmId, Long userId);

    public Collection<Film> getPopularFilms(Long count);
}
