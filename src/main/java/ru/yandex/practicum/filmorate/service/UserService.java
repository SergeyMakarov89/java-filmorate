package ru.yandex.practicum.filmorate.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.InMemoryUserStorage;

import java.util.Collection;

@Service
public class UserService {
    private final InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserService(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    public Collection<User> findAll() {
        return inMemoryUserStorage.findAll();
    }

    public User findUserById(Long userId) {
        return inMemoryUserStorage.findUserById(userId);
    }

    public User create(@Valid @RequestBody(required = true) User user) {
        return inMemoryUserStorage.create(user);
    }

    public User update(@Valid @RequestBody(required = true) User newUser) {
        return inMemoryUserStorage.update(newUser);
    }

    public Collection<User> addFriendToUser(Long userId, Long friendId) {
        return inMemoryUserStorage.addFriendToUser(userId, friendId);
    }

    public Collection<User> deleteFriend(Long userId, Long friendId) {
        return inMemoryUserStorage.deleteFriend(userId, friendId);
    }

    public Collection<User> getFriends(Long userId) {
        return inMemoryUserStorage.getFriends(userId);
    }

    public Collection<User> getCommonFriends(Long userId, Long friendId) {
        return inMemoryUserStorage.getCommonFriends(userId, friendId);
    }
}
