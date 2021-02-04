package com.bool.jetpackmvvm.room.bean;

import androidx.room.Entity;

@Entity(primaryKeys = {"playlistId", "songId"})
public class PlaylistSongCrossRef {

    public long playlistId;
    public long songId;

}
