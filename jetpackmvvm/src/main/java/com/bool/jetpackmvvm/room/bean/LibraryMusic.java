package com.bool.jetpackmvvm.room.bean;

import androidx.room.PrimaryKey;

public class LibraryMusic {

    @PrimaryKey
    public long libraryId;

    //userOwnerId字段就是对另一个实体的主键的引用
    public long userOwnerId;
}
