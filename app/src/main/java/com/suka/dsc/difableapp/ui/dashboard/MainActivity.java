package com.suka.dsc.difableapp.ui.dashboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.suka.dsc.difableapp.R;
import com.suka.dsc.difableapp.adapter.ViewPagerAdapter;
import com.suka.dsc.difableapp.model.UserData;
import com.suka.dsc.difableapp.ui.authentication.login.LoginActivity;
import com.suka.dsc.difableapp.utils.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navMain;
    private DrawerLayout drawer;
    private UserData userData;
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        mSessionManager = new SessionManager(this);
        userData = mSessionManager.getUserData();
        String displayName = userData.getUserName();
        String email = userData.getUserEmail();
        String imgUrl = userData.getUserPhotoUrl();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navMain = findViewById(R.id.nav_view);
        navMain.setNavigationItemSelectedListener(this);
        View header = navMain.getHeaderView(0);
        TextView tvHeaderUsername = header.findViewById(R.id.nav_tv_name);
        TextView tvHeaderEmail = header.findViewById(R.id.nav_tv_email);
        CircleImageView imgAvatar = header.findViewById(R.id.nav_img_ava);

        tvHeaderUsername.setText(displayName);
        tvHeaderEmail.setText(email);

        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(this)
                .load(imgUrl)
                .apply(requestOptions)
                .into(imgAvatar);

        Button btnLogout = navMain.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MainActivity.this);
            }
        });

        ViewPager viewPager = findViewById(R.id.view_pager);
        final TabLayout tabLayout = findViewById(R.id.tab_layout);
        final TextView tabTitle = findViewById(R.id.tab_title);

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tabTitle.setText(getResources().getText(R.string.dashboard_title_request));
                        break;
                    case 1:
                        tabTitle.setText(getResources().getText(R.string.dashboard_title_mybook));
                        break;
                    case 2:
                        tabTitle.setText(getResources().getText(R.string.dashboard_title_allbook));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_request);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_mybook);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_allbooks);

    }

    private void logout() {
        mSessionManager.deleteSession();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void showDialog(Activity activity){
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_logout);

        Button btnYes = (Button) dialog.findViewById(R.id.btn_logout_dialog_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                logout();
            }
        });

        Button btnNo = (Button) dialog.findViewById(R.id.btn_logout_dialog_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
