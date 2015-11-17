package com.example.zhangweijin.coorappbar;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zhangweijin.coorappbar.adapter.PagerAdapter;
import com.example.zhangweijin.coorappbar.fragments.ContentFragment;
import com.example.zhangweijin.coorappbar.fragments.ToolbarContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private FragmentTransaction ft;
    private DrawerLayout mDrawer;
    private NavigationView mNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setUpToolbar();
        setUpNav();
        selectItem(0);
    }
    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        mNav = (NavigationView) findViewById(R.id.nav_menu);


    }
    private void setUpToolbar() {
        mToolbar.setTitle("JustMaterial");
        mToolbar.setNavigationIcon(R.drawable.tool);

        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mDrawer.isDrawerOpen(GravityCompat.START);
                if (isOpen) {
                    mDrawer.closeDrawer(GravityCompat.START);
                } else {
                    mDrawer.openDrawer(GravityCompat.START);
                }

            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setUpNav() {
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                selectItem(item.getItemId());
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }
    private void selectItem(int index) {
        switch (index) {
            case R.id.signin:
                ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit,R.anim.fragment_enter, R.anim.fragment_exit);
                ft.replace(R.id.content, ToolbarContent.newInstance("test", "test"));
//                ft.addToBackStack(null);
                ft.commit();
                ft = null;
                break;
            default:

                ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit,R.anim.fragment_enter, R.anim.fragment_exit);
                ft.add(R.id.content, ToolbarContent.newInstance("test", "test"));
//                ft.addToBackStack(null);
                ft.commit();
                ft = null;
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
