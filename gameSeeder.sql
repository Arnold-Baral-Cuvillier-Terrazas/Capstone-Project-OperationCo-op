USE capstone_db;


LOAD DATA LOCAL INFILE '/Users/amaroterrazas/Documents/csv/games.csv'
    INTO TABLE games
    FIELDS TERMINATED BY '|' ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 LINES
    (title, release_date, description, critics_rating, art_cover, igdb_api_id);

