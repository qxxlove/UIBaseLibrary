package com.bool.jetpackmvvm.room.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserMusic {

    @PrimaryKey
    public long userId;
    public String name;
    public int age;

}
