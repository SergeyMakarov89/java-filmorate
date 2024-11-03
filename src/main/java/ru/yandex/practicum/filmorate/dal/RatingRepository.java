package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public class RatingRepository extends BaseRepository<Rating> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM RATING_MPA ORDER BY RATING_MPA_ID";
    private static final String FIND_BY_NAME_QUERY = "SELECT * FROM RATING_MPA WHERE RATING_MPA_NAME = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM RATING_MPA WHERE RATING_MPA_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO RATING_MPA(RATING_MPA_NAME)" + "VALUES (?)";
    private static final String UPDATE_QUERY = "UPDATE RATING_MPA SET RATING_MPA_NAME = ? WHERE RATING_MPA_ID = ?";

    public RatingRepository(JdbcTemplate jdbc, RowMapper<Rating> mapper) {
        super(jdbc, mapper);
    }

    public List<Rating> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Rating> findByName(String name) {
        return findOne(FIND_BY_NAME_QUERY, name);
    }

    public Optional<Rating> findById(long ratingId) {
        return findOne(FIND_BY_ID_QUERY, ratingId);
    }

    public Rating save(Rating rating) {
        long id = insert(
                INSERT_QUERY,
                rating.getName()
        );
        rating.setId(id);
        return rating;
    }

    public Rating update(Rating rating) {
        update(
                UPDATE_QUERY,
                rating.getName(),
                rating.getId()
        );
        return rating;
    }
}
