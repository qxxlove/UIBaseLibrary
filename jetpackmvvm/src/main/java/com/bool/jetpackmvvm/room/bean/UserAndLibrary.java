package com.bool.jetpackmvvm.room.bean;

import androidx.room.Embedded;
import androidx.room.Relation;

/**
 *
 * 音乐库：
 *
 * 定义一对一关系
 *     我们需要重新创建一个的数据类，
 *     其中每个实例都包含父实体(用户)的一个实例 和 与之对应的子实体(用户自己的音乐库)实例
 *     父实体的每个实例都恰好对应于子实体的一个实例
 *
 *   场景：
 *   假设有一个音乐在线播放应用，用户在该应用中具有一个属于自己的歌曲库。
 *   每个用户只有一个库，每个库恰好对应于一个用户。
 *   因此，User实体和Libray实体之间就存在一种一对一的关系
 *
 *
 */

public class UserAndLibrary {

    @Embedded
    public UserMusic user;

    //将@Relation主键添加到子实体的实例上，
    // 同时将parentColumn设置为父实体主键列的名称，
    // 并将entityColumn设置为引用父实体主键的子实体列的名称。
    // 按理说这两id 一样
    @Relation(
            parentColumn = "userId",
            entityColumn = "userOwnerId"
    )
    public LibraryMusic library;
}
