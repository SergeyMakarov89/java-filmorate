package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.FriendshipDto;
import ru.yandex.practicum.filmorate.service.FriendshipService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FriendshipDto createFriendship(@RequestBody FriendshipDto friendshipRequest) {
        return friendshipService.createFriendship(friendshipRequest);
    }

    @PutMapping("/{friendshipId}")
    public FriendshipDto updateFreindship(@PathVariable("friendshipId") long friendshipId, @RequestBody FriendshipDto request) {
        return friendshipService.updateFriendship(friendshipId, request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FriendshipDto> getFriendships() {
        return friendshipService.getFriendships();
    }

    @GetMapping("/{friendshipId}")
    @ResponseStatus(HttpStatus.OK)
    public FriendshipDto getFriendshipById(@PathVariable("friendshipId") long friendshipId) {
        return friendshipService.getFriendshipById(friendshipId);
    }
}
