package com.bool.jetpackmvvm.room.bean;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class SongWithPlaylists {

    @Embedded
    public Song song;
    @Relation(
            parentColumn = "songId",
            entityColumn = "playlistId",
            associateBy = @Junction(PlaylistSongCrossRef.class)
    )
    public List<Playlist> playlists;
}
