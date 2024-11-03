package ru.yandex.practicum.filmorate.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("USER_ID"));
        user.setName(resultSet.getString("USER_NAME"));
        user.setEmail(resultSet.getString("USER_EMAIL"));
        user.setLogin(resultSet.getString("USER_LOGIN"));
        user.setBirthday(resultSet.getDate("USER_BIRTHDAY").toLocalDate());

        return user;
    }
}
