package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.FilmGenresValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmGenresValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmGenresConstraint {
    String message() default "Такой возрастной рейтинг отсутствует";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
