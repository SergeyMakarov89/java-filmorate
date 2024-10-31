package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.RatingDto;
import ru.yandex.practicum.filmorate.model.Rating;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RatingMapper {
    public Rating mapToRating(RatingDto request) {
        Rating rating = new Rating();
        rating.setName(request.getName());

        return rating;
    }

    public RatingDto mapToRatingDto(Rating rating) {
        RatingDto dto = new RatingDto();
        dto.setId(rating.getId());
        dto.setName(rating.getName());

        return dto;
    }

    public Rating updateRatingFields(Rating rating, RatingDto request) {
        if (request.getName() != null) {
            rating.setName(request.getName());
        }

        return rating;
    }
}
