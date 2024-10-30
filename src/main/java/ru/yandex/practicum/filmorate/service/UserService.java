package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dal.FriendshipRepository;
import ru.yandex.practicum.filmorate.dal.UserRepository;

import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.mapper.UserMapper;
import ru.yandex.practicum.filmorate.dto.UserDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    public UserService(UserRepository userRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
    }

    public UserDto createUser(UserDto request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new ValidationException("Имейл должен быть указан");
        }
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new ValidationException("Имя пользователя должно быть указано");
        }

        Optional<User> alreadyExistUser = userRepository.findByEmail(request.getEmail());
        if (alreadyExistUser.isPresent()) {
            throw new NotFoundException("Данный имейл уже используется");
        }
        User user = UserMapper.mapToUser(request);
        user = userRepository.save(user);
        return UserMapper.mapToUserDto(user);
    }

    public UserDto getUserById(long userId) {
        return userRepository.findById(userId)
                .map(UserMapper::mapToUserDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(long userId, UserDto request) {
        User updatedUser = userRepository.findById(userId)
                .map(user -> UserMapper.updateUserFields(user, request))
                .orElseThrow(() -> new ValidationException("Пользователь не найден"));
        updatedUser = userRepository.update(updatedUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    public UserDto updateUserFull(UserDto userRequest) {
        if (userRequest.getId() == null) {
            throw new NotFoundException("Пользователь не найден");
        }
        User updatedUser = userRepository.findById(userRequest.getId())
                .map(user -> UserMapper.updateUserFields(user, userRequest))
                .orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        updatedUser = userRepository.update(updatedUser);
        return UserMapper.mapToUserDto(updatedUser);
    }

    public List<User> getFriendsByUserId(long userId) {
        return userRepository.findAllFriends(userId);
    }

    public List<UserDto> getCommonFriends(long userId, long friendId) {
        return userRepository.findCommonFriends(userId, friendId)
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }
}
