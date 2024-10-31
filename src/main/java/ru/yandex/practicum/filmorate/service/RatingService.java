package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dal.RatingRepository;
import ru.yandex.practicum.filmorate.dto.RatingDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.RatingMapper;
import ru.yandex.practicum.filmorate.model.Rating;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingService(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    public RatingDto createRating(RatingDto request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new NotFoundException("Название рейтинга должно быть указано");
        }

        Optional<Rating> alreadyExistRating = ratingRepository.findByName(request.getName());
        if (alreadyExistRating.isPresent()) {
            throw new NotFoundException("Данное название уже используется");
        }
        Rating rating = ratingMapper.mapToRating(request);
        rating = ratingRepository.save(rating);
        return ratingMapper.mapToRatingDto(rating);
    }

    public RatingDto getRatingById(long ratingId) {
        return ratingRepository.findById(ratingId)
                .map(ratingMapper::mapToRatingDto)
                .orElseThrow(() -> new NotFoundException("Рейгинг не найден с ID: " + ratingId));
    }

    public List<RatingDto> getRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(ratingMapper::mapToRatingDto)
                .collect(Collectors.toList());
    }

    public RatingDto updateRating(long ratingId, RatingDto request) {
        Rating updatedRating = ratingRepository.findById(ratingId)
                .map(rating -> ratingMapper.updateRatingFields(rating, request))
                .orElseThrow(() -> new NotFoundException("Рейтинг не найден"));
        updatedRating = ratingRepository.update(updatedRating);
        return ratingMapper.mapToRatingDto(updatedRating);
    }
}
