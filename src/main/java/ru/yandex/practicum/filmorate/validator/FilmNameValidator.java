package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.FilmNameConstraint;

public class FilmNameValidator implements ConstraintValidator<FilmNameConstraint, String> {
    @Override
    public void initialize(FilmNameConstraint filmName) {
    }

    @Override
    public boolean isValid(String filmField, ConstraintValidatorContext cxt) {
        return filmField != null && !(filmField.isBlank());
    }
}
