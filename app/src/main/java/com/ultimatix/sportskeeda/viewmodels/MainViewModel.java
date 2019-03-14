package com.ultimatix.sportskeeda.viewmodels;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.ultimatix.sportskeeda.data.SKRepository;
import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.datamodel.Feed;
import com.ultimatix.sportskeeda.data.entities.Tab;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {
    private final SKRepository repo;




    public MainViewModel(@NonNull Application application) {
        super(application);
        repo = new SKRepository(application);
        getFeed();
    }

    public LiveData<List<Item>> getItemsLiveData() {
        return repo.loadItemsFromCache();
    }

    public LiveData<List<Item>> getItemsLiveData(String category) {
        return repo.loadItemsFromCache(category);
    }

    public void getFeed() {
        repo.getFeed(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, final Response<Feed> response) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        repo.deleteCache();
                        repo.addToCache(response.body().getFeed());
                    }
                });

            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {

            }
        });
    }

    public LiveData<List<Tab>> getTabsLiveData() {

        return Transformations.switchMap(repo.getItemTypes(), new Function<List<String>, LiveData<List<Tab>>>() {
            @Override
            public LiveData<List<Tab>> apply(List<String> input) {
                //repo.deleteAllTabs();
                //to do -- delete tabs if not fetched from server

                List<Tab> tabs = new ArrayList<>();
                for(String item: input){
                    tabs.add(new Tab(item));
                }


                try {
                    repo.addTabIfNew(tabs);
                }
                catch (SQLiteConstraintException e){
                    Log.e("", e.getMessage());
                }

                return repo.getTabs();
            }
        });
    }

    public void updateTab(Tab tab) {
        repo.updateTab(tab);
    }
}
