package com.bool.jetpackmvvm.room.bean;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPlaylists {

    @Embedded
    public User user;

    //按理说这两id 一样
    @Relation(
            parentColumn = "userId",
            entityColumn = "userCreatorId"
    )
    public List<Playlist> playlists;
}
