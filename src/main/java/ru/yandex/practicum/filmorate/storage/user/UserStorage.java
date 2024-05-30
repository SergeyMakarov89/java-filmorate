package ru.yandex.practicum.filmorate.storage.user;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;

public interface UserStorage {

    public Collection<User> findAll();

    public User findUserById(Long userId);

    public User create(@Valid @RequestBody(required = true) User user);

    public User update(@Valid @RequestBody(required = true) User newUser);

    public long getNextId();

    public Collection<User> addFriendToUser(Long userId, Long friendId);

    public Collection<User> deleteFriend(Long userId, Long friendId);

    public Collection<User> getFriends(Long userId);

    public Collection<User> getCommonFriends(Long userId, Long friendId);
}
