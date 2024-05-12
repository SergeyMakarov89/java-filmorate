package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Film {
    long id;
    String name;
    String description;
    LocalDate releaseDate;
    Long duration;
}
