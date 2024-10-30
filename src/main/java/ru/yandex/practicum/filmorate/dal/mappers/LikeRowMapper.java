package ru.yandex.practicum.filmorate.dal.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Like;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class LikeRowMapper implements RowMapper<Like> {
    @Override
    public Like mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Like like = new Like();
        like.setId(resultSet.getLong("LIKE_ID"));
        like.setFilmId(resultSet.getLong("FILM_ID"));
        like.setUserId(resultSet.getLong("USER_ID"));

        return like;
    }
}
