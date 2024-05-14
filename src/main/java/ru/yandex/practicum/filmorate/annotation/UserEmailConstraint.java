package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.UserEmailValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserEmailValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserEmailConstraint {
    String message() default "Электронная почта пользователя не может быть пустой и должна содержать символ @";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
