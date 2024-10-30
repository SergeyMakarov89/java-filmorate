package ru.yandex.practicum.filmorate.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.filmorate.dto.FriendshipDto;
import ru.yandex.practicum.filmorate.model.Friendship;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FriendshipMapper {

    public static Friendship mapToFriendship(FriendshipDto request) {
        Friendship friendship = new Friendship();
        friendship.setUserId(request.getUserId());
        friendship.setFriendId(request.getFriendId());
        friendship.setIsConfirmedFriend(request.getIsConfirmedFriend());

        return friendship;
    }

    public static FriendshipDto mapToFriendshipDto(Friendship friendship) {
        FriendshipDto dto = new FriendshipDto();
        dto.setId(friendship.getId());
        dto.setUserId(friendship.getUserId());
        dto.setFriendId(friendship.getFriendId());
        dto.setIsConfirmedFriend(friendship.getIsConfirmedFriend());

        return dto;
    }

    public static Friendship updateFriendshipFields(Friendship friendship, FriendshipDto request) {
        if (request.getUserId() != null) {
            friendship.setUserId(request.getUserId());
        }
        if (request.getFriendId() != null) {
            friendship.setFriendId(request.getFriendId());
        }
        if (request.getIsConfirmedFriend() != null) {
            friendship.setIsConfirmedFriend(request.getIsConfirmedFriend());
        }

        return friendship;
    }
}
