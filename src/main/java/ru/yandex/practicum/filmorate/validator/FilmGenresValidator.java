package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.annotation.FilmGenresConstraint;
import ru.yandex.practicum.filmorate.dal.GenreRepository;
import ru.yandex.practicum.filmorate.dto.GenreDto;
import ru.yandex.practicum.filmorate.exception.ValidationException;

import java.util.List;

public class FilmGenresValidator implements ConstraintValidator<FilmGenresConstraint, List<GenreDto>> {

    @Autowired
    public GenreRepository genreRepository;

    @Override
    public void initialize(FilmGenresConstraint filmGenres) {
    }

    @Override
    public boolean isValid(List<GenreDto> genresField, ConstraintValidatorContext cxt) {
        if (genresField != null) {
            for (GenreDto genre : genresField) {
                if (genreRepository.findById(genre.getId()).isEmpty()) {
                    throw new ValidationException("Такого жанра не существует, добавьте жанр");
                }
            }
            return true;
        } else {
            return true;
        }
    }
}
