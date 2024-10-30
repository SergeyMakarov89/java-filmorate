package ru.yandex.practicum.filmorate.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FilmRowMapper implements RowMapper<Film> {
    @Override
    public Film mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Film film = new Film();
        film.setId(resultSet.getLong("FILM_ID"));
        film.setName(resultSet.getString("FILM_NAME"));
        film.setDescription(resultSet.getString("FILM_DESCRIPTION"));
        film.setReleaseDate(resultSet.getDate("FILM_RELEASE_DATE").toLocalDate());
        film.setDuration(resultSet.getLong("FILM_DURATION"));
        film.setMpa(resultSet.getLong("FILM_RATING_MPA"));

        return film;
    }
}
