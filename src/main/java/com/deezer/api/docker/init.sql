CREATE TABLE playlists (
    playlist_id BIGINT NOT NULL PRIMARY KEY,
    title text NOT NULL
);

CREATE TABLE tracks (
    track_id BIGINT NOT NULL PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE playlist_tracks (
    playlist_id BIGINT NOT NULL,
    track_id BIGINT NOT NULL,
    PRIMARY KEY (playlist_id, track_id),
    FOREIGN KEY (playlist_id)
        REFERENCES playlists (playlist_id),
    FOREIGN KEY (track_id)
        REFERENCES tracks (track_id)
);