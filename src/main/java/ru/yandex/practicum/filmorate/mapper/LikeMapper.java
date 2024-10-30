package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.dto.LikeDto;
import ru.yandex.practicum.filmorate.model.Like;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeMapper {

    public static Like mapToLike(LikeDto request) {
        Like like = new Like();
        like.setFilmId(request.getFilmId());
        like.setUserId(request.getUserId());

        return like;
    }

    public static LikeDto mapToLikeDto(Like like) {
        LikeDto dto = new LikeDto();
        dto.setId(like.getId());
        dto.setFilmId(like.getFilmId());
        dto.setUserId(like.getUserId());

        return dto;
    }

    public static Like updateLikeFields(Like like, LikeDto request) {
        if (request.getFilmId() != null) {
            like.setFilmId(request.getFilmId());
        }
        if (request.getUserId() != null) {
            like.setUserId(request.getUserId());
        }
        return like;
    }
}
