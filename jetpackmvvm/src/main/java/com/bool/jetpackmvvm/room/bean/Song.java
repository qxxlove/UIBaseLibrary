package com.bool.jetpackmvvm.room.bean;

import androidx.room.PrimaryKey;


/**
 * 多对多关系是指: 父实体的每个实例 对应于 子实体的零个或多个实例。
 *    在音乐在线播放应用示例中，每个播放列表都可以包含多首歌曲，
 *    每首歌曲都可以包含在多个不同的播放列表中。
 *    因此，Playlist实体和Song实体之间应存在多对多的关系。
 *
 *    首先创建两个实体类。多对多关系与其他关系类型不同的一点在于，
 *    子实体中通常不存在对父实体的引用。因此，需要创建第三个类来表示两个实体之间的关联实体（即交叉引用表）。
 *    交叉引用表中必须包含多对多关系中每个实体的主键列。
 *    在本例中，交叉引用表中的每一行都对应于 Playlist 实例和 Song 实例的配对，
 *    其中引用的歌曲包含在引用的播放列表中
 *
 *     使用：
 *     如果您想查询播放列表和每个播放列表所含歌曲的列表，则应创建一个新的数据类，
 *     其中包含单个 Playlist 对象，以及该播放列表所包含的所有 Song 对象的列表。
 *
 *     如果您想查询歌曲和每首歌曲所在播放列表的列表，则应创建一个新的数据类，
 *     其中包含单个 Song 对象，以及包含该歌曲的所有 Playlist 对象的列表。
 *
 *     在这两种情况下，都可以通过以下方法在实体之间建立关系：
 *     在上述每个类中的 @Relation 注释中使用 associateBy 属性
 *     来确定提供 Playlist 实体与 Song 实体之间关系的交叉引用实体。
 *
 */

public class Song {

    @PrimaryKey
    public long songId;
    public String songName;
    public String artist;
}
