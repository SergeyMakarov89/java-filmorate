package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.yandex.practicum.filmorate.annotation.UserBirthdayConstraint;
import ru.yandex.practicum.filmorate.annotation.UserEmailConstraint;
import ru.yandex.practicum.filmorate.annotation.UserLoginConstraint;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(of = {"id"})
public class User {
    private Long id;
    @Email
    @UserEmailConstraint
    private String email;
    @UserLoginConstraint
    private String login;
    private String name;
    @UserBirthdayConstraint
    private LocalDate birthday;
    private Set<Long> friends;
}
