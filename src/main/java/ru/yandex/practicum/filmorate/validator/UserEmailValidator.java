package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.UserEmailConstraint;


public class UserEmailValidator implements ConstraintValidator<UserEmailConstraint, String> {
    @Override
    public void initialize(UserEmailConstraint userEmail) {
    }

    @Override
    public boolean isValid(String userField, ConstraintValidatorContext cxt) {
        return userField != null && userField.contains("@");
    }
}
