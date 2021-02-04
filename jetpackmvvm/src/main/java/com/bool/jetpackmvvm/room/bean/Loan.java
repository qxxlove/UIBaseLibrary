package com.bool.jetpackmvvm.room.bean;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Loan {

    @PrimaryKey
    private int book_id;
    @PrimaryKey
    private int user_id;
}
