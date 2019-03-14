package com.ultimatix.sportskeeda.data.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.data.entities.Tab;

import java.util.List;

@Dao
public interface TabsDao {

    @Insert
    void add(List<Tab> tabs);

    @Query("Select * from tab")
    LiveData<List<Tab>> getTabs();

    @Query("Delete from tab")
    void deleteAll();

    @Update
    void update(Tab tab);
}
