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

    public boolean addFriend(Long userId, Long friendId) {
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

        Friendship friendship = new Friendship();
        friendship.setUserId(userId);
        friendship.setFriendId(friendId);
        friendship.setIsConfirmedFriend("NO");

        friendshipRepository.save(friendship);

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
