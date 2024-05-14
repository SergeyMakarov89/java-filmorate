package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.yandex.practicum.filmorate.annotation.UserLoginConstraint;

public class UserLoginValidator implements ConstraintValidator<UserLoginConstraint, String> {
    @Override
    public void initialize(UserLoginConstraint userLogin) {
    }

    @Override
    public boolean isValid(String userField, ConstraintValidatorContext cxt) {
        return userField != null && !(userField.contains(" "));
    }
}
