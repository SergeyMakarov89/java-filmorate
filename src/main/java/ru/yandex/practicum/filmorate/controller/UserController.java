package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping
    public User create(@Valid @RequestBody(required = true) User user) {
        return userService.create(user);
    }


    @PutMapping
    public User update(@Valid @RequestBody(required = true) User newUser) {
        return userService.update(newUser);
    }

    @PutMapping("/{userId}/friends/{friendId}")
    public Collection<User> addFriendToUser(@PathVariable Long userId,
                                @PathVariable Long friendId) {
        return userService.addFriendToUser(userId, friendId);
    }

    @DeleteMapping("/{userId}/friends/{friendId}")
    public Collection<User> deleteFriend(@PathVariable Long userId,
                                @PathVariable Long friendId) {
        return userService.deleteFriend(userId, friendId);
    }

    @GetMapping("/{userId}/friends")
    public Collection<User> getFriends(@PathVariable Long userId) {
        return userService.getFriends(userId);
    }

    @GetMapping("/{userId}/friends/common/{friendId}")
    public Collection<User> getCommonFriends(@PathVariable Long userId,
                                         @PathVariable Long friendId) {
        return userService.getCommonFriends(userId, friendId);
    }
}
