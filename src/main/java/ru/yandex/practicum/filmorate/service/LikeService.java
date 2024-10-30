package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dal.LikeRepository;
import ru.yandex.practicum.filmorate.dto.LikeDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.LikeMapper;
import ru.yandex.practicum.filmorate.model.Like;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public LikeDto createLike(LikeDto request) {
        if (request.getFilmId() == null || request.getFilmId() == 0) {
            throw new NotFoundException("ID фильма должен быть указан");
        }
        if (request.getUserId() == null || request.getUserId() == 0) {
            throw new NotFoundException("ID пользователя должен быть указан");
        }

        Optional<Like> alreadyExistLike = likeRepository.findByUserLike(request.getFilmId(), request.getUserId());
        if (alreadyExistLike.isPresent()) {
            throw new NotFoundException("Лайк этого пользователя уже есть у этого фильма");
        }
        Like like = LikeMapper.mapToLike(request);
        like = likeRepository.save(like);
        return LikeMapper.mapToLikeDto(like);
    }

    public LikeDto getLikeById(long likeId) {
        return likeRepository.findById(likeId)
                .map(LikeMapper::mapToLikeDto)
                .orElseThrow(() -> new NotFoundException("Лайк не найден с ID: " + likeId));
    }

    public List<LikeDto> getLikes() {
        return likeRepository.findAll()
                .stream()
                .map(LikeMapper::mapToLikeDto)
                .collect(Collectors.toList());
    }

    public LikeDto updateLike(long likeId, LikeDto request) {
        Like updatedLike = likeRepository.findById(likeId)
                .map(like -> LikeMapper.updateLikeFields(like, request))
                .orElseThrow(() -> new NotFoundException("Лайк не найден"));
        updatedLike = likeRepository.update(updatedLike);
        return LikeMapper.mapToLikeDto(updatedLike);
    }
}
