create database if not exists dslistapp;

create schema if not exists dslistapp;
set schema 'dslistapp';

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- ############## create table - games:
create table if not exists dslistapp.tb_game(
    id                serial not null,
    title             varchar(255) not null,
    score             numeric(12,5) default null,
    game_year         integer not null,
    genre             varchar(100) default null,
    platforms         varchar(255) default null,
    imgUrl            varchar(255) default null,
    short_description text default null,
    long_description  text default null,
    created_at        timestamp default now() not null,
    primary key (id)
);

create unique index uq_tb_game on dslistapp.tb_game (id);

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- ############## create table - gamesList:

create table if not exists dslistapp.tb_game_list(
    id                serial not null,
    name              varchar(255) not null,
    created_at        timestamp default now() not null,
    primary key (id)
);

create unique index uq_tb_game_list on dslistapp.tb_game_list (id);

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- ############## inserts defaults!!!!

-- gamesList:
INSERT INTO dslistapp.tb_game_list (name, created_at) VALUES ('Aventura e RPG', current_timestamp);
INSERT INTO dslistapp.tb_game_list (name, created_at) VALUES ('Jogos de plataforma', current_timestamp);

-- games:
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (current_timestamp, 'Mass Effect Trilogy', 4.8, 2012, 'Role-playing (RPG), Shooter', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/1.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!Its ok baby');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Red Dead Redemption 2', 4.7, 2018, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/2.png', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'The Witcher 3: Wild Hunt', 4.7, 2014, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/3.png', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Sekiro: Shadows Die Twice', 3.8, 2019, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/4.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Ghost of Tsushima', 4.6, 2012, 'Role-playing (RPG), Adventure', 'XBox, Playstation, PC','https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/5.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Super Mario World', 4.7, 1990, 'Platform', 'Super Ness, PC','https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/6.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Hollow Knight', 4.6, 2017, 'Platform', 'XBox, Playstation, PC', 'https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/7.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Ori and the Blind Forest', 4, 2015, 'Platform', 'XBox, Playstation, PC','https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/8.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Cuphead', 4.6, 2017, 'Platform', 'XBox, Playstation, PC','https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/9.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
INSERT INTO dslistapp.tb_game (created_at, title, score, game_year, genre, platforms, img_url, short_description, long_description) VALUES (CURRENT_TIMESTAMP, 'Sonic CD', 4, 1993, 'Platform', 'Sega CD, PC','https://raw.githubusercontent.com/devsuperior/java-spring-dslist/main/resources/10.png','Lorem ipsum dolor sit amet consectetur adipisicing elit. Odit esse officiis corrupti unde repellat non quibusdam! Id nihil itaque ipsum!','Lorem ipsum dolor sit amet consectetur adipisicing elit. Delectus dolorum illum placeat eligendi, quis maiores veniam. Incidunt dolorum, nisi deleniti dicta odit voluptatem nam provident temporibus reprehenderit blanditiis consectetur tenetur. Dignissimos blanditiis quod corporis iste, aliquid perspiciatis architecto quasi tempore ipsam voluptates ea ad distinctio, sapiente qui, amet quidem culpa.');
