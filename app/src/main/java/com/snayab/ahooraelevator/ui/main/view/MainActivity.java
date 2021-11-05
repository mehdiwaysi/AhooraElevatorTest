package com.snayab.ahooraelevator.ui.main.view;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.snayab.ahooraelevator.R;
import com.snayab.ahooraelevator.databinding.ActivityMainBinding;
import com.snayab.ahooraelevator.helpers.SnackBarHelper;
import com.snayab.ahooraelevator.ui.about_company.view.AboutCompanyActivity;
import com.snayab.ahooraelevator.ui.about_us.view.AboutUsActivity;
import com.snayab.ahooraelevator.ui.base.BaseActivity;
import com.snayab.ahooraelevator.ui.contact_us.view.ContactUsActivity;
import com.snayab.ahooraelevator.ui.enter.view.UserEnterActivity;
import com.snayab.ahooraelevator.ui.fragments.category.view.CategoryFragment;
import com.snayab.ahooraelevator.ui.fragments.main.view.HomeFragment;
import com.snayab.ahooraelevator.ui.fragments.profile.view.ProfileFragment;
import com.snayab.ahooraelevator.ui.rules.view.RulesActivity;
import com.snayab.ahooraelevator.ui.splash.view.SplashActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, HomeFragment.OnImageViewElevatorTypesCLick {

    private String TAG = this.getClass().getName();
    private ActivityMainBinding binding;
    private boolean isDrawerOpen = false;
    private int onBackPressedCount = 0;
    private HomeFragment homeFragment;
    private CategoryFragment categoryFragment;
    private ProfileFragment profileFragment;
    private Fragment active;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initObjects();
        initView();
        setFirstFragmentToShow();

    }

    private void initObjects() {
        profileFragment = new ProfileFragment();
        homeFragment = new HomeFragment();
        categoryFragment = new CategoryFragment();
        homeFragment.setOnImageViewElevatorTypesCLick(this);

        fragmentManager = getSupportFragmentManager();

    }

    private void setFirstFragmentToShow() {

        fragmentManager.beginTransaction().add(R.id.container, homeFragment, "home").show(homeFragment).commit();

        active = homeFragment;
        binding.bottomNavigation.setSelectedItemId(R.id.nav_home);

    }

    private void replaceFragment(Fragment fragment) {

        fragmentManager.beginTransaction().hide(active).show(fragment).commit();

        //fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

    }


    private void initView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);

        binding.imageViewLeftDrawer.setOnClickListener(this);
        binding.slidingMenu.linearLayoutAboutUs.setOnClickListener(this);
        binding.slidingMenu.linearLayoutRule.setOnClickListener(this);
        binding.slidingMenu.linearLayoutContactUs.setOnClickListener(this);
        binding.slidingMenu.linearLayoutAboutCompany.setOnClickListener(this);
        binding.slidingMenu.linearLayoutWebsite.setOnClickListener(this);

        binding.drawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                isDrawerOpen = true;

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                isDrawerOpen = false;

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imageViewLeftDrawer: {

                if (binding.drawerlayout.isDrawerOpen(Gravity.LEFT)) {
                    binding.drawerlayout.closeDrawer(Gravity.LEFT);
                } else {
                    binding.drawerlayout.openDrawer(Gravity.LEFT);
                }
                break;
            }
            case R.id.linearLayoutAboutCompany: {

                Intent intent = new Intent(this, AboutCompanyActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.linearLayoutWebsite: {

                String url = "https://test.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);

                break;
            }
            case R.id.linearLayoutAboutUs: {

                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;

            }
            case R.id.linearLayoutRule: {

                Intent intent = new Intent(this, RulesActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.linearLayoutContactUs: {

                Intent intent = new Intent(this, ContactUsActivity.class);
                startActivity(intent);
                break;

            }


        }
    }

    @Override
    public void onBackPressed() {
        if (isDrawerOpen) {
            binding.drawerlayout.closeDrawer(Gravity.LEFT);
        } else {

            if (active == categoryFragment && !categoryFragment.isRecyclerViewMainCategoryVisible()) {
                categoryFragment.showRecyclerViewMainCategory();
            } else {
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast,
                        (ViewGroup) findViewById(R.id.toast_layout_root));

                Toast toast = new Toast(getApplicationContext());
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);

                onBackPressedCount++;
                if (onBackPressedCount == 1) {
                    toast.show();

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressedCount = 0;
                    }
                }, 1000);
                if (onBackPressedCount >= 2) {
                    super.onBackPressed();
                }
            }

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.nav_home: {

                replaceFragment(homeFragment);
                active = homeFragment;
                break;
            }
            case R.id.nav_category: {

                if (!fragmentManager.getFragments().contains(categoryFragment)) {
                    fragmentManager.beginTransaction().add(R.id.container, categoryFragment, "category").commit();
                }
                replaceFragment(categoryFragment);
                active = categoryFragment;
                break;
            }

            case R.id.nav_profile: {

                if (!fragmentManager.getFragments().contains(profileFragment))
                    fragmentManager.beginTransaction().add(R.id.container, profileFragment, "profile").commit();
                else
                    profileFragment.observeForUserInfo();


                replaceFragment(profileFragment);
                active = profileFragment;
                break;

            }

        }
        return true;

    }


    @Override
    public void onImageViewElevatorTypesCLicked() {
        replaceFragment(categoryFragment);
        binding.bottomNavigation.setSelectedItemId(R.id.nav_category);
        active = categoryFragment;

    }
}