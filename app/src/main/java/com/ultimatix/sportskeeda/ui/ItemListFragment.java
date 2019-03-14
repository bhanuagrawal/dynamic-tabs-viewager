package com.ultimatix.sportskeeda.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimatix.sportskeeda.R;
import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.data.entities.Tab;
import com.ultimatix.sportskeeda.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ItemListFragment extends Fragment implements ItemAdater.ItemAdaterListner {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String itemType;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ItemAdater itemApdater;
    private Observer<List<Item>> feedObserver;
    private MainViewModel mainViewModel;

    @BindView(R.id.recyclerview)
    RecyclerView itemsRV;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public ItemListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemListFragment newInstance(String param1, String param2) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            itemType = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        feedObserver = new Observer<List<Item>>() {
            @Override
            public void onChanged(@Nullable List<Item> items) {
                itemApdater.onDataChange((ArrayList) items);
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        itemApdater = new ItemAdater(getContext(), this, ItemAdater.ITEMS);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_list, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        itemsRV.setAdapter(itemApdater);
        itemsRV.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainViewModel.getFeed();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(itemType.equals(getString(R.string.all))){
            mainViewModel.getItemsLiveData().observe(this, feedObserver);
        }
        else{
            mainViewModel.getItemsLiveData(itemType).observe(this, feedObserver);

        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainViewModel.getItemsLiveData().removeObserver(feedObserver);
    }


    @Override
    public void updateTabsVisibility(Tab tab) {

    }
}
