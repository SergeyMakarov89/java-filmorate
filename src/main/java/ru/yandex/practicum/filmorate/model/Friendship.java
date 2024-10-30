package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = {"id"})
public class Friendship {
    private Long id;
    private Long userId;
    private Long friendId;
    private String isConfirmedFriend;
}
