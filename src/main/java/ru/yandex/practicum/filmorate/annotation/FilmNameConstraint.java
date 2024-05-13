package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.FilmNameValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmNameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmNameConstraint {
    String message() default "Название фильма не может быть пустым";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
