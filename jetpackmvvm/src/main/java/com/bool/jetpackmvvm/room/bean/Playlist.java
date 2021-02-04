package com.bool.jetpackmvvm.room.bean;

import androidx.room.PrimaryKey;

/**
 *
 * 播放列表：
 *
 * 两个实体之间的一对多关系是指：
 *    父实体的每个实例对应于子实体的零个或多个实例，
 *    但子实体的每个实例只能恰好对应于父实体的一个实例。
 *
 *   在音乐在线播放应用示例中，假设用户可以将其歌曲整理到播放列表中。
 *   每个用户可以创建任意数量的播放列表，
 *   但每个播放列表只能由一个用户创建。
 *   因此，User实体和Pyaylist实体之间存在一对多关系。
 *
 *
 */


public class Playlist {

    @PrimaryKey
    public long playlistId;

    //这个变量是对父实体的主键的引用
    public long userCreatorId;

    public String playlistName;
}
