package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {
    private final Map<Long, Film> films = new HashMap<>();
    long currentNewId = 0;

    @GetMapping
    public Collection<Film> findAll() {
        return films.values();
    }

    @PostMapping
    public Film create(@Valid @RequestBody(required = true) Film film) {
        film.setId(getNextId());
        log.info("Присвоели Id новому фильму");
        films.put(film.getId(), film);
        log.info("Положили новый фильм в коллекцию");
        return film;
    }

    @PutMapping
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
        throw new ValidationException("Фильм с id = " + newFilm.getId() + " не найден");
    }

    private long getNextId() {
        log.trace("Определили currentNewId в текущий момент");
        return ++currentNewId;
    }
}
