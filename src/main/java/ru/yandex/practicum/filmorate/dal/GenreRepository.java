package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public class GenreRepository extends BaseRepository<Genre> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM genres ORDER BY GENRE_ID";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM genres WHERE GENRE_NAME = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM genres WHERE GENRE_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO genres(GENRE_NAME)" + "VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE genres SET GENRE_NAME = ? WHERE GENRE_ID = ?";

    public GenreRepository(JdbcTemplate jdbc, RowMapper<Genre> mapper) {
        super(jdbc, mapper);
    }

    public List<Genre> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Genre> findByName(String name) {
        return findOne(FIND_BY_NAME_QUERY, name);
    }

    public Optional<Genre> findById(long genreId) {
        return findOne(FIND_BY_ID_QUERY, genreId);
    }

    public Genre save(Genre genre) {
        long id = insert(
                INSERT_QUERY,
                genre.getName()
        );
        genre.setId(id);
        return genre;
    }

    public Genre update(Genre genre) {
        update(
                UPDATE_QUERY,
                genre.getName(),
                genre.getId()
        );
        return genre;
    }
}
