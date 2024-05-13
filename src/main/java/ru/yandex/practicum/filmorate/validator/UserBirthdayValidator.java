package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.UserBirthdayConstraint;

import java.time.LocalDate;

public class UserBirthdayValidator implements ConstraintValidator<UserBirthdayConstraint, LocalDate> {
    @Override
    public void initialize(UserBirthdayConstraint userBirthday) {
    }

    @Override
    public boolean isValid(LocalDate userField, ConstraintValidatorContext cxt) {
        return userField.isBefore(LocalDate.now());
    }
}
