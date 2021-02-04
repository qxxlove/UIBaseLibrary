package com.bool.jetpackmvvm.room.bean;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

/**
 * 该类可在 Playlist 实体类和 Song 实体类之间建立多对多关系
 */
public class PlaylistWithSongs {

    @Embedded
    public Playlist playlist;
    @Relation(
            parentColumn = "playlistId",
            entityColumn = "songId",
            associateBy = @Junction(PlaylistSongCrossRef.class)
    )
    public List<Song> songs;
}
