package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepository extends BaseRepository<Film> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM films";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM films WHERE FILM_NAME = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM films WHERE FILM_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO films(FILM_NAME, FILM_DESCRIPTION, FILM_RELEASE_DATE, FILM_DURATION, FILM_RATING_MPA)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE films SET FILM_NAME = ?, FILM_DESCRIPTION = ?, FILM_RELEASE_DATE = ?, FILM_DURATION = ?, FILM_RATING_MPA = ? WHERE FILM_ID = ?";
    private static final String FIND_POPULAR_FILMS_QUERY = "SELECT F.* FROM FILMS F JOIN (SELECT FILM_ID FROM LIKES GROUP BY FILM_ID ORDER BY COUNT(USER_ID) DESC LIMIT ?) L ON F.FILM_ID = L.FILM_ID";

    public FilmRepository(JdbcTemplate jdbc, RowMapper<Film> mapper) {
        super(jdbc, mapper);
    }

    public List<Film> findAll() {
        List<Film> films = findMany(FIND_ALL_QUERY);
        return films;
    }

    public Optional<Film> findByName(String name) {
        return findOne(FIND_BY_NAME_QUERY, name);
    }

    public Optional<Film> findById(long filmId) {
        return findOne(FIND_BY_ID_QUERY, filmId);
    }

    public List<Film> getPopularFilms(Integer count) {
        return findMany(FIND_POPULAR_FILMS_QUERY, count);
    }

    public Film save(Film film) {
        long id = insert(
                INSERT_QUERY,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpa()
        );
        film.setId(id);
        return film;
    }

    public Film update(Film film) {
        update(
                UPDATE_QUERY,
                film.getName(),
                film.getDescription(),
                film.getReleaseDate(),
                film.getDuration(),
                film.getMpa(),
                film.getId()
        );
        return film;
    }
}
