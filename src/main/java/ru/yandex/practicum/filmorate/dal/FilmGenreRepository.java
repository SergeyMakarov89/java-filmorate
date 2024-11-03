package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.FilmGenre;

import java.util.List;
import java.util.Optional;

@Repository
public class FilmGenreRepository extends BaseRepository<FilmGenre> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM films_genres ORDER BY FILM_GENRE_ID";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM films_genres WHERE FILM_GENRE_ID = ?";
    private static final String FIND_BY_FILM_QUERY = "SELECT * FROM films_genres WHERE FILM_ID = ?";
    private static final String FIND_BY_GENRE_QUERY = "SELECT * FROM films_genres WHERE GENRE_ID = ?";
    private static final String FIND_BY_FILM_AND_GENRE_QUERY = "SELECT * FROM films_genres WHERE FILM_ID = ? AND GENRE_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO films_genres(FILM_ID, GENRE_ID)" + "VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE films_genres SET FILM_ID = ?, GENRE_ID = ? WHERE FILM_GENRE_ID = ?";

    public FilmGenreRepository(JdbcTemplate jdbc, RowMapper<FilmGenre> mapper) {
        super(jdbc, mapper);
    }

    public List<FilmGenre> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<FilmGenre> findById(Long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }

    public List<FilmGenre> findByFilmId(Long id) {
        return findMany(FIND_BY_FILM_QUERY, id);
    }

    public List<FilmGenre> findByGenreId(Long id) {
        return findMany(FIND_BY_GENRE_QUERY, id);
    }

    public Optional<FilmGenre> findByFilmAndGenreId(Long filmId, Long genreId) {
        return findOne(FIND_BY_FILM_AND_GENRE_QUERY, filmId, genreId);
    }

    public FilmGenre save(FilmGenre filmGenre) {
        long id = insert(
                INSERT_QUERY,
                filmGenre.getFilmId(),
                filmGenre.getGenreId()
        );
        filmGenre.setId(id);
        return filmGenre;
    }

    public FilmGenre update(FilmGenre filmGenre) {
        update(
                UPDATE_QUERY,
                filmGenre.getFilmId(),
                filmGenre.getGenreId(),
                filmGenre.getId()
        );
        return filmGenre;
    }
}
