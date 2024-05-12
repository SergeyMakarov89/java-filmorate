package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody(required = true) Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.error("Название пустое при создании фильма");
            throw new ValidationException("Название не может быть пустым");
        }
        if (film.getDescription().length() > 200) {
            log.error("Максимальная длина описания больше 200 символов при создании фильма");
            throw new ValidationException("Максимальная длина описания — 200 символов");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            log.error("Дата релиза раньше 28 декабря 1895 года при создании фильма");
            throw new ValidationException("Дата релиза не должна быть раньше 28 декабря 1895 года");
        }
        if (film.getDuration() < 0) {
            log.error("Продолжительность фильма отрицательное число при создании фильма");
            throw new ValidationException("Продолжительность фильма должна быть положительным числом");
        }
        film.setId(getNextId());
        log.info("Присвоели Id новому фильму");
        films.put(film.getId(), film);
        log.info("Положили новый фильм в коллекцию");
        return film;
    }

    @PutMapping
    public Film update(@Valid @RequestBody(required = true) Film newFilm) {
        if (films.containsKey(newFilm.getId())) {
            if (newFilm.getName() == null) {
                log.error("Название пустое при обновлении фильма");
                throw new ValidationException("Название не может быть пустым");
            }
            if (newFilm.getDescription().length() > 200) {
                log.error("Максимальная длина описания больше 200 символов при обновлении фильма");
                throw new ValidationException("Максимальная длина описания — 200 символов");
            }
            if (newFilm.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
                log.error("Дата релиза раньше 28 декабря 1895 года при обновлении фильма");
                throw new ValidationException("Дата релиза не должна быть раньше 28 декабря 1895 года");
            }
            if (newFilm.getDuration() < 0) {
                log.error("Продолжительность фильма отрицательное число при обновлении фильма");
                throw new ValidationException("Продолжительность фильма должна быть положительным числом");
            }
            Film oldFilm = films.get(newFilm.getId());
            log.debug("Создали oldFilm и присвоели ему значение соотвествоющие такому же Id нового фильма");
            oldFilm.setName(newFilm.getName());
            log.info("Присвоели название oldFilm из newFilm");
            oldFilm.setDescription(newFilm.getDescription());
            log.info("Присвоели описание oldFilm из newFilm");
            oldFilm.setReleaseDate(newFilm.getReleaseDate());
            log.info("Присвоели дату выхода oldFilm из newFilm");
            oldFilm.setDuration(newFilm.getDuration());
            log.info("Присвоели продолжиельность oldFilm из newFilm");
            return oldFilm;
        }
        log.error("Фильм с id = " + newFilm.getId() + " не найден в коллекции");
        throw new ValidationException("Фильм с id = " + newFilm.getId() + " не найден");
    }

    private long getNextId() {
        long currentMaxId = films.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        log.trace("Определили currentMaxId в films в текущий момент");
        return ++currentMaxId;
    }
}
