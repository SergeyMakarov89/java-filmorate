package ru.yandex.practicum.filmorate.storage.film;

import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InMemoryFilmStorage implements FilmStorage {

    private final Map<Long, Film> films = new HashMap<>();
    private long currentNewId = 0;

    public Collection<Film> findAll() {
        return films.values();
    }

    public Film findFilmById(Long filmId) {
        return films.values().stream()
                .filter(f -> f.getId().equals(filmId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Фильм с id: %d не найден", filmId)));
    }

    public Film create(@Valid @RequestBody(required = true) Film film) {
        film.setId(getNextId());
        log.info("Присвоели Id новому фильму");
        films.put(film.getId(), film);
        log.info("Положили новый фильм в коллекцию");
        return film;
    }

    public Film update(@Valid @RequestBody(required = true) Film newFilm) {
        if (films.containsKey(newFilm.getId())) {
            Film updatedFilm = films.get(newFilm.getId());
            log.info("Создали updatedFilm и присвоели ему значение соотвествоющие такому же Id нового фильма");
            updatedFilm.setName(newFilm.getName());
            log.info("Присвоели название updatedFilm из newFilm");
            updatedFilm.setDescription(newFilm.getDescription());
            log.info("Присвоели описание updatedFilm из newFilm");
            updatedFilm.setReleaseDate(newFilm.getReleaseDate());
            log.info("Присвоели дату выхода updatedFilm из newFilm");
            updatedFilm.setDuration(newFilm.getDuration());
            log.info("Присвоели продолжиельность updatedFilm из newFilm");
            return updatedFilm;
        }
        log.error("Фильм с id = " + newFilm.getId() + " не найден в коллекции");
        throw new NotFoundException("Фильм с id = " + newFilm.getId() + " не найден");
    }

    public long getNextId() {
        log.info("Определили currentNewId в текущий момент");
        return ++currentNewId;
    }

    public Film addLikeToFilm(Long filmId,  Long userId) {
        if (filmId != null && userId != null) {
            if (films.containsKey(filmId)) {
                if (InMemoryUserStorage.users.containsKey(userId)) {
                    if (films.get(filmId).getLikes() == null) {
                        films.get(filmId).setLikes(new HashSet<>());
                        log.debug("Создали новый HashSet и присвоили его значению поля likes");
                        films.get(filmId).getLikes().add(userId);
                        log.debug("Добавили лайк пользователя фильму");
                    } else {
                        films.get(filmId).getLikes().add(userId);
                        log.debug("Добавили лайк пользователя фильму");
                    }
                    return films.get(filmId);
                } else {
                    log.error("Пользователь с id = " + userId + " не найден в коллекции");
                    throw new NotFoundException("Пользователь не найден, проверьте корректность ввода id");
                }
            } else {
                log.error("Фильм с id = " + filmId + " не найден в коллекции");
                throw new NotFoundException("Фильм не найден, проверьте корректность ввода id");
            }
        } else {
            log.error("id некорректны");
            throw new ValidationException("id некорректны");
        }
    }

    public Film deleteLikeFromFilm(Long filmId, Long userId) {
        if (filmId != null && userId != null) {
            if (films.containsKey(filmId)) {
                if (films.get(filmId).getLikes().contains(userId)) {
                    films.get(filmId).getLikes().remove(userId);
                    log.debug("Удалили лайк пользователя у фильма");
                    return films.get(filmId);
                } else {
                    log.error("У фильма с id:" + filmId + " нет лайка от пользователя с id:" + userId + ", проверьте корректность ввода id");
                    throw new NotFoundException("У этого фильма нет лайка от этого пользователя, проверьте корректность ввода id");
                }
            } else {
                log.error("Фильм с id = " + filmId + " не найден в коллекции");
                throw new NotFoundException("Фильм не найден, проверьте корректность ввода id");
            }
        } else {
            log.error("id некорректны");
            throw new ValidationException("id некорректны");
        }
    }

    public Collection<Film> getPopularFilms(Long count) {
        if (count != null) {
            return  films.values()
                    .stream()
                    .sorted(Comparator.comparingInt((Film film) -> Optional.ofNullable(film.getLikes()).map(Set::size).orElse(0)).reversed())
                    .limit(count)
                    .collect(Collectors.toList());
        } else {
            log.error("Не правильный ввод параметра count");
            throw new ValidationException("Не правильный ввод параметра count");
        }
    }
}
