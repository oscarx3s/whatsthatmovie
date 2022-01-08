CREATE TABLE IF NOT EXISTS "public".cast (
  castid serial primary key,
  movieid int NOT NULL,
  celebrityname varchar(300) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS "comments" (
  "commentid" serial primary key,
  "movieid" int NOT NULL,
  "userid" int NOT NULL,
  "comment" varchar(300) DEFAULT NULL,
  "ishidden" char(1) NOT NULL DEFAULT 'N'
);

CREATE TABLE IF NOT EXISTS "movies" (
  "movieid" serial primary key,
  "moviename" varchar(300) NOT NULL,
  "year" varchar(30) NOT NULL,
  "genre" varchar(300) DEFAULT 'UNKNOWN',
  "thumbnail" bytea,
  "desc" varchar(10000) NOT NULL
);


INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(21, 'Croods', '1999', 'Animation, Family, Comedy',null, 'The Croods is a 2013 American computer-animated adventure comedy film produced by DreamWorks Animation and distributed by 20th Century Fox.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(22, 'Deadpool 1', '2012', 'Action, Comedy', null, 'Ajax, a twisted scientist, experiments on Wade Wilson, a mercenary, to cure him of cancer and give him healing powers. However, the experiment leaves Wade disfigured and he decides to exact revenge.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(23, 'Knock Knock', '2010', 'Thriller', null, 'Evan, a dedicated father and husband, opens his house to Genesis and Bel, two women who claim to be stranded. Things take a turn for the worse when they each begin to seduce him.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(24, 'Lego Scooby Doo', '2020', 'Lego Animation', null, 'Scooby Doo and the gang try to rescue a dilapidated movie studio, which is haunted by several ghosts and monsters.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(49530, 'In Time', '2011', 'Action, Thriller, Science fiction', null, 'In the not-too-distant future the aging gene has been switched off. To avoid overpopulation, time has become the currency and the way people pay for luxuries and necessities. The rich can live forever, while the rest try to negotiate for their immortality. A poor young man who comes into a fortune of time, though too late to help his mother from dying. He ends up on the run from a corrupt police force known as \'time keepers\'.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(58559, 'Confession of a Child of the Century', '2012', 'Drama', null, 'Paris, 1830: Octave, betrayed by his mistress, sinks into despair and debauchery. His father\'s death leads him to the country where he meets Brigitte, a widow who is ten years his elder. Octave falls in love passionately, but will he have the courage to believe in it?');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(74458, 'Mere Brother Ki Dulhan', '2011', 'Drama, Comedy, Romance', null, 'London-based Luv Agnihotri decides to end his bachelorhood and asks his Bollywood film-maker brother, Kush, to find a bride for him - much to the displeasure of his Dehradun-based father. Kush accordingly meets and interviews a variety of women, and finally selects Delhi-based Dimple Dixit, a woman he had known before, to be the perfect match. Dimple and Luv meet on-line, are attracted to each other, and the former travels to India where the two families get the couple formally engaged. It is th');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(77561, 'EVA', '2011', 'Science fiction', null, 'Set in 2041, humans of planet Earth now live side-by-side with androids. A well-known cybernetic engineer, Alex Garel, is tasked with an assignment to create a robot in the form of a human child. He returns home to begin his work and finds that his childhood love Lana has married his brother David. Their daughter Eva seems to possess a superior intelligence and powerful charisma that immediately captivates Alex. Looking for inspiration, Alex asks Eva to be the muse of the new robot, which challe');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(85414, 'Brake', '2012', 'Action, Thriller', null, 'A Secret Service Agent is held captive in the trunk of a car and endures high-speed mental and physical torture as terrorists attempt to extract needed information for their sinister plot.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(89492, 'This Is 40', '2012', 'Comedy', null, 'Pete and Debbie are both about to turn 40, their kids hate each other, both of their businesses are failing, they\'re on the verge of losing their house, and their relationship is threatening to fall apart.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(93840, 'Blind Man', '2012', 'Action, Thriller', null, 'The mutilated body of a young woman is found in her home. There is no evidence of burglary and there are no witnesses. Commissioner Lasalle suspects that it was a blind man who committed the crime. But the man has a credible alibi.');

INSERT INTO "movies" ("movieid", "moviename", "year", "genre", "thumbnail", "desc") VALUES
(96821, 'Caesar Must Die', '2012', 'Drama, Documentary', null, 'Winner of the Golden Bear at the 2012 Berlin Film Festival, Caesar Must Die (Cesare Deve Morire), the scripted documentary shows inmates at a prison in Rome rehearsing for a performance of Shakespeare\'s Julius Caesar.');

CREATE TABLE IF NOT EXISTS "ratings" (
  "userid" serial primary key,
  "movieid" int NOT NULL,
  "rating" int NOT NULL DEFAULT '0'
);

CREATE TABLE IF NOT EXISTS "users" (
  "userid" serial primary key,
  "fname" varchar(30) NOT NULL,
  "lname" varchar(30) NOT NULL,
  "gender" char(1) DEFAULT 'U',
  "email" varchar(30) DEFAULT NULL,
  "password" varchar(100) NOT NULL,
  "role" char(5) DEFAULT 'NUSER',
  "cancomment" char(1) DEFAULT 'Y',
  "isactive" char(1) DEFAULT 'Y',
  "dp" varchar(300) DEFAULT 'UNKNOWN'
);

INSERT INTO "users" ("userid", "fname", "lname", "gender", "email", "password", "role", "cancomment", "isactive", "dp") VALUES
(1, 'System', 'Administrator', 'O', 'admin@wtm.com', 'admin', 'ADMIN', 'Y', 'Y', 'UNKNOWN'),
(4, 'Ishara', 'Dissanayake', 'M', 'ishara@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
(5, 'Akalanka', 'Sakalasooriya', 'M', 'akalanka@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
(6, 'Dinushi', 'Jayasinghe', 'F', 'dinushi@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
(3, 'Oshada', 'Bandaranayake', 'F', 'oshada@wtm.com', '123', 'ADMIN', 'Y', 'Y', 'UNKNOWN'),
(2, 'System', 'User', 'O', 'user@wtm.com', 'user', 'NUSER', 'Y', 'Y', 'UNKNOWN');

INSERT INTO "users" ("fname", "lname", "gender", "email", "password", "role", "cancomment", "isactive", "dp") VALUES
('System', 'Administrator', 'O', 'admin@wtm.com', 'admin', 'ADMIN', 'Y', 'Y', 'UNKNOWN'),
('System', 'User', 'O', 'user@wtm.com', 'user', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
('Oshada', 'Bandaranayake', 'F', 'oshada@wtm.com', '123', 'ADMIN', 'Y', 'Y', 'UNKNOWN'),
('Ishara', 'Dissanayake', 'M', 'ishara@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
('Akalanka', 'Sakalasooriya', 'M', 'akalanka@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN'),
('Dinushi', 'Jayasinghe', 'F', 'dinushi@wtm.com', '123', 'NUSER', 'Y', 'Y', 'UNKNOWN');