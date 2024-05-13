package ru.yandex.practicum.filmorate.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.yandex.practicum.filmorate.validator.FilmDescriptionValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilmDescriptionValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FilmDescriptionConstraint {
    String message() default "Максимальная длина описания фильма — 200 символов";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
