package com.bool.jetpackmvvm.room.bean;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

/**
 * 该类可在 User 实体类和 PlaylistWithSongs 关系类之间建立一对多关系：
 */
public class UserWithPlaylistsAndSongs {

    @Embedded
    public User user;
    @Relation(
            entity = Playlist.class,
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<PlaylistWithSongs> playlists;
}
