package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.practicum.filmorate.annotation.FilmMpaConstraint;
import ru.yandex.practicum.filmorate.dal.RatingRepository;
import ru.yandex.practicum.filmorate.dto.RatingDto;
import ru.yandex.practicum.filmorate.exception.ValidationException;

public class FilmMpaValidator implements ConstraintValidator<FilmMpaConstraint, RatingDto> {

    @Autowired
    public RatingRepository ratingRepository;

    @Override
    public void initialize(FilmMpaConstraint filmMpa) {
    }

    @Override
    public boolean isValid(RatingDto mpaField, ConstraintValidatorContext cxt) {
        if (ratingRepository.findById(mpaField.getId()).isEmpty()) {
            throw new ValidationException("Такой возрастной рейтинг отсутствует");
        } else {
            return true;
        }
    }
}
