package com.ultimatix.sportskeeda.data.entities;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = {"type"},
        unique = true)})
public class Tab {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    private boolean visible;

    public Tab(String type) {
        this.type = type;
        this.visible = true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
