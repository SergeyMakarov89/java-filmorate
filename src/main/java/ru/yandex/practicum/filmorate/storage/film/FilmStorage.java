package ru.yandex.practicum.filmorate.storage.film;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;

public interface FilmStorage {

    public Collection<Film> findAll();

    public Film findFilmById(Long filmId);

    public Film create(@Valid @RequestBody(required = true) Film film);

    public Film update(@Valid @RequestBody(required = true) Film newFilm);

    public long getNextId();

    public Film addLikeToFilm(Long filmId, Long userId);

    public Film deleteLikeFromFilm(Long filmId, Long userId);

    public Collection<Film> getPopularFilms(Long count);
}
