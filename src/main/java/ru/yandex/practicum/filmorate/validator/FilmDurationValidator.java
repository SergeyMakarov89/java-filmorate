package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.FilmDurationConstraint;

public class FilmDurationValidator implements ConstraintValidator<FilmDurationConstraint, Long> {
    @Override
    public void initialize(FilmDurationConstraint filmDuration) {
    }

    @Override
    public boolean isValid(Long filmField, ConstraintValidatorContext cxt) {
        return filmField > 0;
    }
}
