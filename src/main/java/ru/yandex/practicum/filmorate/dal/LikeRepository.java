package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.List;
import java.util.Optional;

@Repository
public class LikeRepository extends BaseRepository<Like> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM LIKES";
    private static final String FIND_BY_FILM_ID_QUERY = "SELECT * FROM LIKES WHERE FILM_ID = ?";
    private static final String FIND_BY_LIKES_BY_FILM_ID_QUERY = "SELECT COUNT(LIKE_ID) AS LIKES_COUNT FROM LIKES WHERE FILM_ID = ?;";
    private static final String DELETE_LIKE_BY_FILM_ID_USER_ID_QUERY = "DELETE FROM LIKES WHERE FILM_ID = ? AND USER_ID = ?";
    private static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM LIKES WHERE USER_ID = ?";
    private static final String FIND_BY_USER_LIKE_QUERY = "SELECT * FROM LIKES WHERE FILM_ID = ? AND USER_ID = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM LIKES WHERE LIKE_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO LIKES(FILM_ID, USER_ID)" + "VALUES (?, ?)";
    private static final String UPDATE_QUERY = "UPDATE LIKES SET FILM_ID = ?, USER_ID = ? WHERE LIKE_ID = ?";

    public LikeRepository(JdbcTemplate jdbc, RowMapper<Like> mapper) {
        super(jdbc, mapper);
    }

    public List<Like> findAll() { return findMany(FIND_ALL_QUERY);}
    public Optional<Like> findByFilmId(Long filmId) {
        return findOne(FIND_BY_FILM_ID_QUERY, filmId);
    }
    public Long findLikesByFilmId(Long filmId) {
        return findResult(FIND_BY_LIKES_BY_FILM_ID_QUERY, filmId);
    }
    public Optional<Like> findByUserId(Long userId) {
        return findOne(FIND_BY_USER_ID_QUERY, userId);
    }
    public Optional<Like> findById(Long id) {
        return findOne(FIND_BY_ID_QUERY, id);
    }

    public Optional<Like> findByUserLike(Long filmId, Long userId) {
        return findOne(FIND_BY_USER_LIKE_QUERY, filmId, userId);
    }

    public Like save(Like like) {
        long id = insert(
                INSERT_QUERY,
                like.getFilmId(),
                like.getUserId()

        );
        like.setId(id);
        return like;
    }

    public long saveLike(long filmId, long userId) {
        long id = insert(
                INSERT_QUERY,
                filmId,
                userId

        );
        return id;
    }

    public boolean deleteLike(long filmId, long userId) {
        return delete(
                DELETE_LIKE_BY_FILM_ID_USER_ID_QUERY,
                filmId,
                userId

        );
    }

    public Like update(Like like) {
        System.out.println(like.getFilmId());
        System.out.println(like.getUserId());
        update(
                UPDATE_QUERY,
                like.getFilmId(),
                like.getUserId(),
                like.getId()
        );
        return like;
    }
}
