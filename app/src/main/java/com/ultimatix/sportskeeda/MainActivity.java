package com.ultimatix.sportskeeda;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.ultimatix.sportskeeda.ui.OnFragmentInteractionListener;

import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

public class MainActivity extends AppCompatActivity
        implements OnFragmentInteractionListener {

    private NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
    }


    @Override
    public void openPage(NavDirections action) {
        navHostFragment.getNavController().navigate(action);
    }
}
