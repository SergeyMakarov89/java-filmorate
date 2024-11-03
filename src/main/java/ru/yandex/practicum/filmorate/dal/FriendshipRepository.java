package ru.yandex.practicum.filmorate.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.List;
import java.util.Optional;

@Repository
public class FriendshipRepository extends BaseRepository<Friendship> {
    private static final String FIND_ALL_QUERY = "SELECT * FROM friendship";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM friendship WHERE FRIENDSHIP_ID = ?";
    private static final String FIND_BY_USER_ID_QUERY = "SELECT * FROM friendship WHERE USER_ID = ?";
    private static final String FIND_FRIENDSHIP_BY_USER_QUERY = "SELECT * FROM friendship WHERE USER_ID = ? AND FRIEND_ID = ?";
    private static final String FIND_BY_FRIENDSHIP_STATUS_QUERY = "SELECT * FROM friendship WHERE IS_CONFIRMED_FRIEND = ?";
    private static final String INSERT_QUERY = "INSERT INTO friendship(USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND)" +
            "VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE friendship SET USER_ID = ?, FRIEND_ID = ?, IS_CONFIRMED_FRIEND = ? WHERE FRIENDSHIP_ID = ?";
    private static final String FIND_FRIENDSHIP = "SELECT * FROM friendship WHERE USER_ID = ? AND FRIEND_ID = ?";
    private static final String DELETE_FRIENDSHIP_BY_USER_ID_FRIEND_ID_QUERY = "DELETE FROM friendship WHERE USER_ID = ? AND FRIEND_ID = ?";

    public FriendshipRepository(JdbcTemplate jdbc, RowMapper<Friendship> mapper) {
        super(jdbc, mapper);
    }

    public List<Friendship> findAll() {
        return findMany(FIND_ALL_QUERY);
    }

    public Optional<Friendship> findById(long friendshipId) {
        return findOne(FIND_BY_ID_QUERY, friendshipId);
    }

    public Optional<Friendship> findByUserId(long id) {
        return findOne(FIND_BY_USER_ID_QUERY, id);
    }

    public List<Friendship> findByManyUserId(long id) {
        return findMany(FIND_BY_USER_ID_QUERY, id);
    }

    public List<Friendship> findByFriendshipStatus(String status) {
        return findMany(FIND_BY_FRIENDSHIP_STATUS_QUERY, status);
    }

    public Optional<Friendship> findFriendshipByUser(Long userId, Long friendId) {
        return findOne(FIND_FRIENDSHIP_BY_USER_QUERY, userId, friendId);
    }

    public Friendship save(Friendship friendship) {
        long id = insert(
                INSERT_QUERY,
                friendship.getUserId(),
                friendship.getFriendId(),
                friendship.getIsConfirmedFriend()
        );
        friendship.setId(id);
        return friendship;
    }

    public Friendship update(Friendship friendship) {
        update(
                UPDATE_QUERY,
                friendship.getUserId(),
                friendship.getFriendId(),
                friendship.getIsConfirmedFriend(),
                friendship.getId()
        );
        return friendship;
    }


    public Optional<Friendship> findFriendship(long userId, long friendId) {
        return findOne(FIND_FRIENDSHIP, userId, friendId);
    }

    public boolean deleteFriend(long userId, long friendId) {
        return delete(
                DELETE_FRIENDSHIP_BY_USER_ID_FRIEND_ID_QUERY,
                userId,
                friendId
        );
    }
}
