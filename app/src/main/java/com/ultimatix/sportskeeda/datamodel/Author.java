package com.ultimatix.sportskeeda.datamodel;

import android.arch.persistence.room.ColumnInfo;

import java.io.Serializable;

public class Author implements Serializable {

    @ColumnInfo(name = "author_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
