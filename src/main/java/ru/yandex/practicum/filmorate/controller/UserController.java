package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Long, User> users = new HashMap<>();

    @GetMapping
    public Collection<User> findAll() {
        return users.values();
    }

    @PostMapping
    public User create(@Valid @RequestBody(required = true) User user) {
        if (user.getEmail() == null || !(user.getEmail().contains("@"))) {
            log.error("Электронная почта пустая или не содержит символ @ при создании пользователя");
            throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
        }
        if (user.getLogin() == null || user.getEmail().contains(" ")) {
            log.error("Логин пустой или содержит пробелы при создании пользователя");
            throw new ValidationException("Логин не может быть пустым и содержать пробелы");
        }
        if (user.getName() == null) {
            log.debug("Имя пустое при создании пользователя");
            user.setName(user.getLogin());
            log.info("Присвоели имени нового пользователя значение логина при создании пользователя");
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            log.error("Дата рождения больше текущей даты при создании пользователя");
            throw new ValidationException("Дата рождения не может быть в будущем");
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
            if (newUser.getEmail() == null || !(newUser.getEmail().contains("@"))) {
                log.error("Электронная почта пустая или не содержит символ @ при обновлении пользователя");
                throw new ValidationException("Электронная почта не может быть пустой и должна содержать символ @");
            }
            if (newUser.getLogin() == null || newUser.getEmail().contains(" ")) {
                log.error("Логин пустой или содержит пробелы при обновлении пользователя");
                throw new ValidationException("Логин не может быть пустым и содержать пробелы");
            }
            if (newUser.getBirthday().isAfter(LocalDate.now())) {
                log.error("Дата рождения больше текущей даты при обновлении пользователя");
                throw new ValidationException("Дата рождения не может быть в будущем");
            }
            User oldUser = users.get(newUser.getId());
            log.debug("Создали oldUser и присвоели ему значение соотвествоющие такому же Id нового пользователя");
            oldUser.setEmail(newUser.getEmail());
            log.info("Присвоели электронную почту oldUser из newUser");
            oldUser.setLogin(newUser.getLogin());
            log.info("Присвоели логин oldUser из newUser");
            if (newUser.getName() == null) {
                log.debug("Имя пустое при обновлении пользователя");
                newUser.setName(newUser.getLogin());
                log.info("Присвоели имени нового пользователя значение логина при обновлении пользователя");
            } else {
                oldUser.setName(newUser.getName());
                log.info("Присвоели имя oldUser из newUser");
            }
            oldUser.setBirthday(newUser.getBirthday());
            log.info("Присвоели день рождения oldUser из newUser");
            return oldUser;
        }
        log.error("Пользователь с id = " + newUser.getId() + " не найден в коллекции");
        throw new ValidationException("Пользователь с id = " + newUser.getId() + " не найден");
    }


    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        log.trace("Определили currentMaxId в users в текущий момент");
        return ++currentMaxId;
    }

}
