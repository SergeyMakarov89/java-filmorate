package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.annotation.UserBirthdayConstraint;
import ru.yandex.practicum.filmorate.annotation.UserEmailConstraint;
import ru.yandex.practicum.filmorate.annotation.UserLoginConstraint;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"id"})
public class User {
    private Long id;
    private String name;
    @Email
    @UserEmailConstraint
    private String email;
    @UserLoginConstraint
    private String login;
    @UserBirthdayConstraint
    private LocalDate birthday;
}
