package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.LikeDto;
import ru.yandex.practicum.filmorate.service.LikeService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeDto createLike(@RequestBody LikeDto likeRequest) {
        return likeService.createLike(likeRequest);
    }

    @PutMapping("/{likeId}")
    public LikeDto updateLike(@PathVariable("likeId") long likeId, @RequestBody LikeDto request) {
        return likeService.updateLike(likeId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LikeDto> getLikes() {
        return likeService.getLikes();
    }

    @GetMapping("/{likeId}")
    @ResponseStatus(HttpStatus.OK)
    public LikeDto getLikeById(@PathVariable("likeId") long likeId) {
        return likeService.getLikeById(likeId);
    }
}
