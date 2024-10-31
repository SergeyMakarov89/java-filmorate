package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.LikeDto;
import ru.yandex.practicum.filmorate.model.Like;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeMapper {

    public Like mapToLike(LikeDto request) {
        Like like = new Like();
        like.setFilmId(request.getFilmId());
        like.setUserId(request.getUserId());

        return like;
    }

    public LikeDto mapToLikeDto(Like like) {
        LikeDto dto = new LikeDto();
        dto.setId(like.getId());
        dto.setFilmId(like.getFilmId());
        dto.setUserId(like.getUserId());

        return dto;
    }

    public Like updateLikeFields(Like like, LikeDto request) {
        if (request.getFilmId() != null) {
            like.setFilmId(request.getFilmId());
        }
        if (request.getUserId() != null) {
            like.setUserId(request.getUserId());
        }
        return like;
    }
}
