package com.bool.jetpackmvvm.room.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

/**
 * 比如用户信息，我不需要全部的信息，只关心first_name 和 last_name
 *
 *  也可以使用 @Embedded 注释
 */
public class NameTuple {

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    @NonNull
    public String lastName;
}
