package ru.yandex.practicum.filmorate.storage.user;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

@Slf4j
@Component
public class InMemoryUserStorage implements UserStorage {

    public static Map<Long, User> users = new HashMap<>();
    private long currentNewId = 0;

    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    @Override
    public User findUserById(Long userId) {
        return users.values().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Пользователь с id: %d не найден", userId)));
    }

    @Override
    public User create(@Valid @RequestBody(required = true) User user) {
        if (user.getName() == null) {
            log.info("Имя пустое при создании пользователя");
            user.setName(user.getLogin());
            log.info("Присвоели имени нового пользователя значение логина при создании пользователя");
        }
        user.setId(getNextId());
        log.info("Присвоели Id новому пользователю");
        users.put(user.getId(), user);
        log.info("Положили нового пользователя в коллекцию");
        return user;
    }

    @Override
    public User update(@Valid @RequestBody(required = true) User newUser) {

        if (users.containsKey(newUser.getId())) {
            User updatedUser = users.get(newUser.getId());
            log.info("Создали updatedUser и присвоели ему значение соотвествоющие такому же Id нового пользователя");
            updatedUser.setEmail(newUser.getEmail());
            log.info("Присвоели электронную почту updatedUser из newUser");
            updatedUser.setLogin(newUser.getLogin());
            log.info("Присвоели логин updatedUser из newUser");
            if (newUser.getName() == null) {
                log.info("Имя пустое при обновлении пользователя");
                newUser.setName(newUser.getLogin());
                log.info("Присвоели имени нового пользователя значение логина при обновлении пользователя");
            } else {
                updatedUser.setName(newUser.getName());
                log.info("Присвоели имя updatedUser из newUser");
            }
            updatedUser.setBirthday(newUser.getBirthday());
            log.info("Присвоели день рождения updatedUser из newUser");
            return updatedUser;
        }
        log.error("Пользователь с id = " + newUser.getId() + " не найден в коллекции");
        throw new NotFoundException("Пользователь с id = " + newUser.getId() + " не найден");
    }

    @Override
    public Collection<User> addFriendToUser(Long userId, Long friendId) {
        if (userId != null && friendId != null) {
            if (users.containsKey(userId) && users.containsKey(friendId)) {
                if (users.get(userId).getFriends() == null) {
                    users.get(userId).setFriends(new HashSet<>());
                    log.info("Создали новый HashSet и присвоили его значению поля friends");
                    users.get(userId).getFriends().add(friendId);
                    log.info("Добавили друга с id:" + friendId + " пользователю c id:" + userId);
                } else {
                    users.get(userId).getFriends().add(friendId);
                    log.info("Добавили друга с id:" + friendId + " пользователю c id:" + userId);
                }
                if (users.get(friendId).getFriends() == null) {
                    users.get(friendId).setFriends(new HashSet<>());
                    log.info("Создали новый HashSet и присвоили его значению поля friends");
                    users.get(friendId).getFriends().add(userId);
                    log.info("Добавили другу с id:" + friendId + " пользователя c id:" + userId + " в друзья тоже");
                } else {
                    users.get(friendId).getFriends().add(userId);
                    log.info("Добавили другу с id:" + friendId + " пользователя c id:" + userId + " в друзья тоже");
                }
                List<User> userList = new ArrayList<>();
                log.info("Создали новый список userList для пользователей для http-ответа");
                userList.add(users.get(userId));
                log.info("Добавили в список пользователя с id: " + userId + " для http-ответа");
                userList.add(users.get(friendId));
                log.info("Добавили в список друга с id: " + friendId + " для http-ответа");
                return userList;
            } else {
                log.error("Один из пользователей не найден, проверьте корректность ввода обоих id");
                throw new NotFoundException("Один из пользователей не найден, проверьте корректность ввода обоих id");
            }
        } else {
            log.error("id некорректны");
            throw new ValidationException("id некорректны");
        }
    }

    @Override
    public Collection<User> deleteFriend(Long userId, Long friendId) {
        if (userId != null && friendId != null) {
            if (users.containsKey(userId) && users.containsKey(friendId)) {
                if (users.get(userId).getFriends() != null && users.get(friendId).getFriends() != null) {
                    users.get(userId).getFriends().remove(friendId);
                    log.info("Удалили у пользователя с id:" + userId + " друга с id:" + friendId);
                    users.get(friendId).getFriends().remove(userId);
                    log.info("Удалили у друга с id:" + friendId + " пользователя с id:" + userId);
                    List<User> userList = new ArrayList<>();
                    log.info("Создали новый список userList для http-ответа");
                    userList.add(users.get(userId));
                    log.info("Добавили в список пользователя с id: " + userId + " для http-ответа");
                    userList.add(users.get(friendId));
                    log.info("Добавили в список друга с id: " + friendId + " для http-ответа");
                    return userList;
                } else {
                    List<User> userList = new ArrayList<>();
                    log.info("Создали новый список userList для http-ответа");
                    userList.add(users.get(userId));
                    log.info("Добавили в список пользователя с id: " + userId + " для http-ответа");
                    userList.add(users.get(friendId));
                    log.info("Добавили в список друга с id: " + friendId + " для http-ответа");
                    return userList;
                }
            } else {
                log.error("Один из пользователей не найден, проверьте корректность ввода обоих id");
                throw new NotFoundException("Один из пользователей не найден, проверьте корректность ввода обоих id");
            }
        } else {
            log.error("id некорректны");
            throw new ValidationException("id некорректны");
        }
    }

    @Override
    public Collection<User> getFriends(Long userId) {
        if (userId != null) {
            if (users.containsKey(userId)) {
                if (users.get(userId).getFriends() != null) {
                    List<User> userList = new ArrayList<>();
                    log.info("Создали новый список userList для http-ответа");
                    for (Long friendId : users.get(userId).getFriends()) {
                        userList.add(users.get(friendId));
                        log.trace("Добавили в список userList друга пользоваля с id:" + userId);
                    }
                    return userList;
                } else {
                    return new ArrayList<>();
                }
            } else {
                log.error("Пользователь с id = " + userId + " не найден в коллекции");
                throw new NotFoundException("Пользователь не найден, проверьте корректность ввода id");
            }
        } else {
            log.error("id некорректен");
            throw new ValidationException("id некорректен");
        }
    }

    @Override
    public Collection<User> getCommonFriends(Long userId, Long friendId) {
        if (userId != null && friendId != null) {
            if (users.containsKey(userId) && users.containsKey(friendId)) {
                if (users.get(userId).getFriends() != null && users.get(friendId).getFriends() != null) {
                    List<User> userList = new ArrayList<>();
                    log.info("Создали новый список userList для http-ответа");
                    for (Long userFriendId : users.get(userId).getFriends()) {
                        if (users.get(friendId).getFriends().contains(userFriendId)) {
                            userList.add(users.get(userFriendId));
                            log.trace("Добавили друга с id:" + userFriendId + " в список общих друзей пользоваля с id:" + userId);
                        }
                    }
                    return userList;
                } else {
                    log.error("Список друзей пользователя c id:" + userId + " пуст");
                    throw new NotFoundException("Список друзей одного из пользователей пуст");
                }
            } else {
                log.error("Один из пользователей не найден, проверьте корректность ввода обоих id");
                throw new NotFoundException("Один из пользователей не найден, проверьте корректность ввода обоих id");
            }
        } else {
            log.error("id некорректны");
            throw new ValidationException("id некорректны");
        }
    }

    private long getNextId() {
        log.info("Определили currentNewId в текущий момент");
        return ++currentNewId;
    }
}
