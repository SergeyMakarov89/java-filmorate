package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.FilmDescriptionConstraint;

public class FilmDescriptionValidator implements ConstraintValidator<FilmDescriptionConstraint, String> {
    @Override
    public void initialize(FilmDescriptionConstraint filmDescription) {
    }

    @Override
    public boolean isValid(String filmField, ConstraintValidatorContext cxt) {
        return filmField.length() < 200;
    }
}
