package com.ultimatix.sportskeeda.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.ultimatix.sportskeeda.R;
import com.ultimatix.sportskeeda.data.entities.Item;
import com.ultimatix.sportskeeda.data.entities.Tab;
import com.ultimatix.sportskeeda.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.navigation.NavDirections;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String SETTING = "adsdasdfsdfdsf";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MainViewModel mainViewModel;
    private ItemAdater itemApdater;


    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @BindView(R.id.tablayout)
    TabLayout tabLayout;


    private Observer<List<Tab>> tabsObserver;
    private ViewPagerAdapter viewPagerAdapter;

    private List<Tab> tabs;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



        mainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        
        tabsObserver = new Observer<List<Tab>>() {
            @Override
            public void onChanged(@Nullable List<Tab> tabs) {
                ArrayList<Tab> visibleTabs = new ArrayList<>();
                visibleTabs.add(new Tab(getString(R.string.all)));
                for(Tab tab: tabs){
                    if(tab.isVisible()){
                        visibleTabs.add(tab);
                    }
                }

                viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), visibleTabs));
            }
        };

        tabs = new ArrayList<>();
        setHasOptionsMenu(true);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel.getTabsLiveData().observe(this, tabsObserver);

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
        mainViewModel.getTabsLiveData().removeObserver(tabsObserver);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter{


        private final List<Tab> tabs;

        public ViewPagerAdapter(FragmentManager fm, List<Tab> tabs) {
            super(fm);
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int i) {
            return ItemListFragment.newInstance(tabs.get(i).getType(), "");
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        public void updatePages(List<Tab> tabs) {
            this.tabs.clear();
            this.tabs.addAll(tabs);
            notifyDataSetChanged();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position).getType();
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                DialogFragment f = SettingDialogFragment.newInstance("", "");
                f.show(getChildFragmentManager(), SETTING);
                //mListener.openPage(MainFragmentDirections.actionMainFragmentToSettingDialogFragment2());
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
