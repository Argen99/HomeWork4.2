package com.geektech.homework42;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.geektech.homework42.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_profile)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



        navController.navigate(R.id.boardFragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                ArrayList<Integer> fragments = new ArrayList<>();
                fragments.add(R.id.navigation_home);
                fragments.add(R.id.navigation_dashboard);
                fragments.add(R.id.navigation_notifications);
                fragments.add(R.id.navigation_profile);

                if (fragments.contains(navDestination.getId()))
                    binding.navView.setVisibility(View.VISIBLE);
                else
                    binding.navView.setVisibility(View.GONE);

                if (navDestination.getId() == R.id.boardFragment)
                    binding.navView.setVisibility(View.GONE);
                else
                    binding.navView.setVisibility(View.VISIBLE);

                if (navDestination.getId() == R.id.boardFragment)
                    getSupportActionBar().hide();
                else
                    getSupportActionBar().show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }

    public void onStartClick(View view) {
        navController.navigate(R.id.navigation_home);
    }
}