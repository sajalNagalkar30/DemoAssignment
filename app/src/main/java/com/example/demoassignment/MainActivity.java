package com.example.demoassignment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tabs);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.search_icon));
        getSupportActionBar().setTitle("Deals");
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();  // This is necessary t
        // Set up the ViewPager with the sections adapter.
        setupViewPager();

        // Link the TabLayout with the ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Top");
                    break;
                case 1:
                    tab.setText("Popular");
                    break;
                case 2:
                    tab.setText("Featured");
                    break;
            }
        }).attach();

        // Setup Drawer navigation
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_first:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.nav_second:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.nav_third:
                    viewPager.setCurrentItem(2);
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void setupViewPager() {
        FragmentStateAdapter adapter = new FragmentStateAdapter(this) {
            @Override
            public int getItemCount() {
                return 3; // three fragments
            }

            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new FirstFragment();
                    case 1:
                        return new SecondFragment();
                    case 2:
                        return new ThirdFragment();
                    default:
                        return null;
                }
            }
        };
        viewPager.setAdapter(adapter);
    }
}
