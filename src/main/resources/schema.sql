DROP TABLE IF EXISTS rating_mpa CASCADE;
DROP TABLE IF EXISTS genres CASCADE;
DROP TABLE IF EXISTS films CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS likes CASCADE;
DROP TABLE IF EXISTS friendship CASCADE;
DROP TABLE IF EXISTS films_genres CASCADE;

CREATE TABLE IF NOT EXISTS rating_mpa (
    rating_mpa_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    rating_mpa_name VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS genres (
    genre_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    genre_name VARCHAR(200) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS films (
    film_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    film_name VARCHAR(50) NOT NULL,
    film_description VARCHAR(200) NOT NULL,
    film_release_date DATE NOT NULL,
    film_duration BIGINT NOT NULL,
    film_rating_MPA BIGINT REFERENCES rating_MPA (rating_MPA_id)
);

CREATE TABLE IF NOT EXISTS films_genres (
    film_genre_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    film_id BIGINT REFERENCES films (film_id),
    genre_id BIGINT REFERENCES genres (genre_id)
);

CREATE TABLE IF NOT EXISTS users (
    user_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    user_name VARCHAR(200) NOT NULL,
    user_email VARCHAR(200) NOT NULL,
    user_login VARCHAR(200) NOT NULL,
    user_birthday DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS likes (
    like_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    film_id BIGINT REFERENCES films (film_id),
    user_id BIGINT REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS friendship (
    friendship_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY NOT NULL,
    user_id BIGINT REFERENCES users (user_id),
    friend_id BIGINT REFERENCES users (user_id),
    is_confirmed_friend VARCHAR(200) NOT NULL
);

