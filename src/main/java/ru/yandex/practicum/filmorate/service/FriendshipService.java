package ru.yandex.practicum.filmorate.service;

import org.springframework.stereotype.Service;

import ru.yandex.practicum.filmorate.dal.FriendshipRepository;
import ru.yandex.practicum.filmorate.dto.FriendshipDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.mapper.FriendshipMapper;
import ru.yandex.practicum.filmorate.model.Friendship;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;

    public FriendshipService(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    public FriendshipDto createFriendship(FriendshipDto request) {
        if (request.getUserId() == null || request.getUserId() == 0) {
            throw new NotFoundException("ID пользователя должно быть и не может быть равным 0");
        }
        if (request.getFriendId() == null || request.getFriendId() == 0) {
            throw new NotFoundException("ID друга должно быть и не может быть равным 0");
        }
        if (request.getIsConfirmedFriend() == null || request.getIsConfirmedFriend().isEmpty()) {
            throw new NotFoundException("Статус дружбы должен быть");
        }


        Optional<Friendship> alreadyExistFriendship = friendshipRepository.findFriendshipByUser(request.getUserId(), request.getFriendId());
        if (alreadyExistFriendship.isPresent()) {
            throw new NotFoundException("Этот пользователь уже дружит c этим другом");
        }

        Friendship friendship = FriendshipMapper.mapToFriendship(request);
        friendship = friendshipRepository.save(friendship);
        return FriendshipMapper.mapToFriendshipDto(friendship);
    }

    public FriendshipDto getFriendshipByUserId(long userId) {
        return friendshipRepository.findByUserId(userId)
                .map(FriendshipMapper::mapToFriendshipDto)
                .orElseThrow(() -> new NotFoundException("Пользователь не найден с ID: " + userId));
    }

    public List<FriendshipDto> getFriendships() {
        return friendshipRepository.findAll()
                .stream()
                .map(FriendshipMapper::mapToFriendshipDto)
                .collect(Collectors.toList());
    }

    public FriendshipDto updateFriendship(long friendshipId, FriendshipDto request) {
        Friendship updatedFriendship = friendshipRepository.findById(friendshipId)
                .map(friendship -> FriendshipMapper.updateFriendshipFields(friendship, request))
                .orElseThrow(() -> new NotFoundException("Дружба не найдена"));
        updatedFriendship = friendshipRepository.update(updatedFriendship);
        return FriendshipMapper.mapToFriendshipDto(updatedFriendship);
    }

    public FriendshipDto getFriendshipById(long friendshipId) {
        return friendshipRepository.findById(friendshipId)
                .map(FriendshipMapper::mapToFriendshipDto)
                .orElseThrow(() -> new NotFoundException("Дружба не найдена с ID: " + friendshipId));
    }

    public boolean addFriend(Long userId,  Long friendId) {
        if (userId == null) {
            throw new NotFoundException("ID пользователя должно быть указано");
        }
        if (friendId == null) {
            throw new NotFoundException("ID друга должно быть указано");
        }

        Optional<Friendship> alreadyExistFriendship = friendshipRepository.findFriendship(userId, friendId);
        if (alreadyExistFriendship.isPresent()) {
            throw new NotFoundException("Такая дружба уже есть");
        }

        Friendship friendship = FriendshipMapper.mapToFriendship(new FriendshipDto());
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        friendship.setIsConfirmedFriend("NO");

        friendship = friendshipRepository.save(friendship);

        return true;
    }

    public boolean deleteFriend(long userId, long friendId) {
        Optional<Friendship> alreadyExistFriendship = friendshipRepository.findFriendship(userId, friendId);
        if (alreadyExistFriendship.isPresent()) {
            return friendshipRepository.deleteFriend(userId, friendId);
        } else {
            System.out.println("Такой дружбы не существует");
            return false;
        }
    }
}
