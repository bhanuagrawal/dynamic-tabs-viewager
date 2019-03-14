package com.ultimatix.sportskeeda.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.data.entities.Tab;
import com.ultimatix.sportskeeda.datamodel.Feed;
import com.ultimatix.sportskeeda.network.SKService;
import com.ultimatix.sportskeeda.network.WebServices;

import java.util.List;

import retrofit2.Callback;

public class SKRepository {


    private final SKService skService;
    Application application;
    WebServices webService;


    public SKRepository(Application application) {
        this.application = application;
        webService = new WebServices(application);
        skService = webService.getSKClient().create(SKService.class);
    }

    public void getFeed(Callback<Feed> callback) {
        skService.getFeed().enqueue(callback);
    }

    public void deleteCache() {
        AppDatabase.getAppDatabase(application.getApplicationContext()).itemsDao().deleteAll();
    }

    public void addToCache(List<Item> items) {
        AppDatabase.getAppDatabase(application.getApplicationContext()).itemsDao().add(items);
    }

    public LiveData<List<Item>> loadItemsFromCache() {
        return AppDatabase.getAppDatabase(application.getApplicationContext()).itemsDao().getAllItems();
    }

    public LiveData<List<Item>> loadItemsFromCache(String category) {
        return AppDatabase.getAppDatabase(application.getApplicationContext()).itemsDao().getItems(category);

    }

    public LiveData<List<String>> getItemTypes() {
        return AppDatabase.getAppDatabase(application.getApplicationContext()).itemsDao().getItemsTypes();

    }

    public void addTabIfNew(List<Tab> tabs) {
        AppDatabase.getAppDatabase(application.getApplicationContext()).tabsDao().add(tabs);

    }

    public LiveData<List<Tab>> getTabs() {
        return AppDatabase.getAppDatabase(application.getApplicationContext()).tabsDao().getTabs();
    }

    public void deleteAllTabs() {
        AppDatabase.getAppDatabase(application.getApplicationContext()).tabsDao().deleteAll();

    }

    public void updateTab(Tab tab) {
        AppDatabase.getAppDatabase(application.getApplicationContext()).tabsDao().update(tab);

    }
}
