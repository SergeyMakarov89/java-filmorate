INSERT INTO GENRES (GENRE_NAME) VALUES ('Комедия');
INSERT INTO GENRES (GENRE_NAME) VALUES ('Драма');
INSERT INTO GENRES (GENRE_NAME) VALUES ('Мультфильм');
INSERT INTO GENRES (GENRE_NAME) VALUES ('Триллер');
INSERT INTO GENRES (GENRE_NAME) VALUES ('Документальный');
INSERT INTO GENRES (GENRE_NAME) VALUES ('Боевик');

INSERT INTO RATING_MPA (RATING_MPA_NAME) VALUES ('G');
INSERT INTO RATING_MPA (RATING_MPA_NAME) VALUES ('PG');
INSERT INTO RATING_MPA (RATING_MPA_NAME) VALUES ('PG-13');
INSERT INTO RATING_MPA (RATING_MPA_NAME) VALUES ('R');
INSERT INTO RATING_MPA (RATING_MPA_NAME) VALUES ('NC-17');

INSERT INTO FILMS (FILM_NAME, FILM_DESCRIPTION, FILM_RELEASE_DATE, FILM_DURATION, FILM_RATING_MPA) VALUES ('Экстремальная работа', 'У детективов не получается ловить подозреваемых, зато прекрасно выходит жареная курочка', '2019-10-01', '6660', '3');
INSERT INTO FILMS (FILM_NAME, FILM_DESCRIPTION, FILM_RELEASE_DATE, FILM_DURATION, FILM_RATING_MPA) VALUES ('Шоу Трумана', 'Будет ли Труман продолжать жить в безопасном мире, где, как он теперь знает, у него практически нет свободы выбора', '1998-11-21', '6180', '2');
INSERT INTO FILMS (FILM_NAME, FILM_DESCRIPTION, FILM_RELEASE_DATE, FILM_DURATION, FILM_RATING_MPA) VALUES ('Ходячий замок', 'Девушка-бабушка бежит из города куда глаза глядят и встречает удивительный дом на ножках', '2004-08-14', '7140', '1');
INSERT INTO FILMS (FILM_NAME, FILM_DESCRIPTION, FILM_RELEASE_DATE, FILM_DURATION, FILM_RATING_MPA) VALUES ('Чебурашка', 'Одержимое апельсинами животное оказывается в домике нелюдимого старика-садовника Геннадия', '2022-01-18', '6780', '1');

INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('1', '1');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('1', '2');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('1', '6');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('2', '1');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('2', '2');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('3', '3');
INSERT INTO FILMS_GENRES (FILM_ID, GENRE_ID) VALUES ('4', '1');

INSERT INTO USERS (USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY) VALUES ('Василий', 'vasya@mail.ru', 'vasya33', '2000-01-18');
INSERT INTO USERS (USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY) VALUES ('Дмитрий', 'dima@mail.ru', 'dima55', '1992-11-08');
INSERT INTO USERS (USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY) VALUES ('Аня', 'anya@mail.ru', 'anya77', '2004-05-05');
INSERT INTO USERS (USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY) VALUES ('Владимир', 'vova@mail.ru', 'vova11', '1993-03-15');
INSERT INTO USERS (USER_NAME, USER_EMAIL, USER_LOGIN, USER_BIRTHDAY) VALUES ('Дарья', 'dasha@mail.ru', 'dasha18', '2001-04-11');

INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('1', '1');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('1', '2');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('1', '3');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('1', '4');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('2', '3');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('3', '2');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('3', '3');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('3', '4');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('4', '3');
INSERT INTO LIKES (FILM_ID, USER_ID) VALUES ('4', '5');

INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('1', '4', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('1', '5', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('2', '5', 'NO');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('3', '1', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('3', '2', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('3', '4', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('4', '5', 'YES');
INSERT INTO FRIENDSHIP (USER_ID, FRIEND_ID, IS_CONFIRMED_FRIEND) VALUES ('5', '1', 'NO');
