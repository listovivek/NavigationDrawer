package com.example.user.navigationdrawer;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;

     static View.OnClickListener mOnClickListener;

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOnClickListener = new mYClickListener(this);
        ft= getSupportFragmentManager().beginTransaction();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_ly);

       recyclerView = (RecyclerView) findViewById(R.id.nav_recycleview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        CustomRecyleAdapter adapter = new CustomRecyleAdapter(MainActivity.this, TITLES);
        recyclerView.setAdapter(adapter);
        setupDrawer();

        try {
            if (savedInstanceState == null) {
               selectFirstItemAsDefault();
            }

            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setHomeButtonEnabled(true);
        } catch (Exception e) {

        }
    }

    private void selectFirstItemAsDefault() {
        // mNavigationManager.showFragmentAction(firstActionMovie);
        try {
            this.getSupportActionBar().setTitle("HHIi");
        } catch (Resources.NotFoundException e) {

        }

    }

    private void setupDrawer() {
        this.mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getSupportActionBar().setTitle(getResources().getStringArray(R.array.items)[0]);
                invalidateOptionsMenu();
            }
        };

        this.mDrawerToggle.setDrawerIndicatorEnabled(true);
        this.mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private static class mYClickListener implements View.OnClickListener{

        private final Context context;

        public mYClickListener(MainActivity con) {
            context = con;
        }

        @Override
        public void onClick(View view) {
            createFragment(view);
        }

        private void createFragment(View v){
            int position = recyclerView.getChildLayoutPosition(v);
            Fragment fragment = null;
            String tag = null;

            switch (position){
                case 0:
                fragment = new HomeFragment();
                case 1:
                    fragment = new EventFragment();
            }

            MainActivity a = new MainActivity();
            a.start(fragment);

        }

    }

    private void start(Fragment fragment) {
        try{
            ft.replace(R.id.ContainerFragment, fragment).commit();
        }catch (Exception e){
            Log.d("error", e.toString());
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        this.mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /// This is navigation open and close.

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
