package com.ultimatix.sportskeeda.data.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.ultimatix.sportskeeda.data.entities.Item;
import java.util.List;

@Dao
public interface ItemsDao {

    @Query("SELECT * from Item")
    LiveData<List<Item>> getAllItems();

    @Query("Delete from Item")
    void deleteAll();

    @Insert
    void add(List<Item> items);


    @Query("SELECT * from Item where type = :type")
    LiveData<List<Item>> getItems(String type);


    @Query("SELECT DISTINCT  type from Item")
    LiveData<List<String>> getItemsTypes();
}
