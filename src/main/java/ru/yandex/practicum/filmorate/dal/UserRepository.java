package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends BaseRepository<User> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_COMMON_FRIENDS_QUERY = "SELECT * FROM USERS WHERE USER_ID IN (SELECT f1.FRIEND_ID FROM friendship f1 JOIN friendship f2 ON f1.FRIEND_ID = f2.FRIEND_ID WHERE f1.USER_ID = ? AND f2.USER_ID = ?)";
    private static final String FIND_ALL_FRIENDS_BY_USER_ID_QUERY = "SELECT * FROM USERS WHERE USER_ID IN (SELECT FRIEND_ID FROM FRIENDSHIP WHERE USER_ID = ?)";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE USER_EMAIL = ?";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE USER_ID = ?";
    private static final String INSERT_QUERY = "INSERT INTO users(USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY)" +
            "VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE users SET USER_NAME = ?, USER_EMAIL = ?, USER_LOGIN = ?, USER_BIRTHDAY = ? WHERE USER_ID = ?";

    public UserRepository(JdbcTemplate jdbc, RowMapper<User> mapper) {
        super(jdbc, mapper);
    }

    public List<User> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public List<User> findCommonFriends(long userId, long friendId) {
        return findMany(FIND_COMMON_FRIENDS_QUERY, userId, friendId);
    }

    public List<User> findAllFriends(long userId) {
        return findMany(FIND_ALL_FRIENDS_BY_USER_ID_QUERY, userId);
    }

    public Optional<User> findByEmail(String email) {
        return findOne(FIND_BY_EMAIL_QUERY, email);
    }

    public Optional<User> findById(long userId) {
        return findOne(FIND_BY_ID_QUERY, userId);
    }

    public User save(User user) {
        long id = insert(
                INSERT_QUERY,
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getBirthday()
        );
        user.setId(id);
        return user;
    }

    public User update(User user) {
        update(
                UPDATE_QUERY,
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getBirthday(),
                user.getId()
        );
        return user;
    }
}
