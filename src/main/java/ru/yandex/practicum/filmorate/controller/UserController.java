package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();
    long currentNewId = 0;

    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }

    @PostMapping
    public User create(@Valid @RequestBody(required = true) User user) {
        if (user.getName() == null) {
            log.debug("Имя пустое при создании пользователя");
            user.setName(user.getLogin());
            log.info("Присвоели имени нового пользователя значение логина при создании пользователя");
        }
        user.setId(getNextId());
        log.info("Присвоели Id новому пользователю");
        users.put(user.getId(), user);
        log.info("Положили нового пользователя в коллекцию");
        return user;
    }


    @PutMapping
    public User update(@Valid @RequestBody(required = true) User newUser) {

        if (users.containsKey(newUser.getId())) {
            User updatedUser = users.get(newUser.getId());
            log.debug("Создали updatedUser и присвоели ему значение соотвествоющие такому же Id нового пользователя");
            updatedUser.setEmail(newUser.getEmail());
            log.info("Присвоели электронную почту updatedUser из newUser");
            updatedUser.setLogin(newUser.getLogin());
            log.info("Присвоели логин updatedUser из newUser");
            if (newUser.getName() == null) {
                log.debug("Имя пустое при обновлении пользователя");
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
        throw new ValidationException("Пользователь с id = " + newUser.getId() + " не найден");
    }

    private long getNextId() {
        log.trace("Определили currentNewId в текущий момент");
        return ++currentNewId;
    }

}
