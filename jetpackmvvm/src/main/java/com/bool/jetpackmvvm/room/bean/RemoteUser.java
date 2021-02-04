package com.bool.jetpackmvvm.room.bean;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * 如果实体继承了父实体的字段，则使用@Entity属性的ignroedColumns属性
 */

@Entity(ignoredColumns = "picture")
public class RemoteUser extends  User {


    @PrimaryKey
    public int id;

    public boolean hasVpn;


    @Ignore
    Bitmap picture;
}
