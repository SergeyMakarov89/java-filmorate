package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FriendshipService;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final FriendshipService friendshipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userRequest) {
        return userService.createUser(userRequest);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable("userId") long userId, @RequestBody UserDto request) {
        return userService.updateUser(userId, request);
    }

    @PutMapping
    public UserDto updateUserFull(@RequestBody UserDto userRequest) {
        return userService.updateUserFull(userRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable("userId") long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/{userId}/friends")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getFriendsByUserId(@PathVariable("userId") long userId) {
        if (userService.getUserById(userId) == null) {
            throw new NotFoundException("Пользователь не найден с ID");
        }
        return userService.getFriendsByUserId(userId);
    }

    @PutMapping("/{userId}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean addFriend(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
        if (userService.getUserById(userId) == null || userService.getUserById(friendId) == null) {
            throw new NotFoundException("Пользователь не найден с ID");
        }
        return friendshipService.addFriend(userId, friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteFriend(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
        if (userService.getUserById(userId) == null || userService.getUserById(friendId) == null) {
            throw new NotFoundException("Пользователь не найден с ID");
        }
        return friendshipService.deleteFriend(userId, friendId);
    }

    @GetMapping("/{userId}/friends/common/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getCommonFriends(@PathVariable("userId") long userId, @PathVariable("friendId") long friendId) {
        if (userService.getUserById(userId) == null || userService.getUserById(friendId) == null) {
            throw new NotFoundException("Пользователь не найден с ID");
        }
        return userService.getCommonFriends(userId, friendId);
    }

}
