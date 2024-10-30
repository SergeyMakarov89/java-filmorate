package ru.yandex.practicum.filmorate.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FriendshipRowMapper implements RowMapper<Friendship> {
    @Override
    public Friendship mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Friendship friendship = new Friendship();
        friendship.setId(resultSet.getLong("FRIENDSHIP_ID"));
        friendship.setUserId(resultSet.getLong("USER_ID"));
        friendship.setFriendId(resultSet.getLong("FRIEND_ID"));
        friendship.setIsConfirmedFriend(resultSet.getString("IS_CONFIRMED_FRIEND"));

        return friendship;
    }
}
