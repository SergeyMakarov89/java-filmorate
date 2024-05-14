package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.FilmReleaseDateConstraint;

import java.time.LocalDate;

public class FilmReleaseDateValidator implements ConstraintValidator<FilmReleaseDateConstraint, LocalDate> {

    @Override
    public void initialize(FilmReleaseDateConstraint filmReleaseDate) {
    }

    @Override
    public boolean isValid(LocalDate filmField, ConstraintValidatorContext cxt) {
        return filmField.isAfter(LocalDate.of(1895, 12, 28));
    }
}
