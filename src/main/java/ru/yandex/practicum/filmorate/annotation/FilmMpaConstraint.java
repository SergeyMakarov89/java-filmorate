package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.FilmMpaValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmMpaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmMpaConstraint {
    String message() default "Такого жанра не существует, добавьте жанр";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
