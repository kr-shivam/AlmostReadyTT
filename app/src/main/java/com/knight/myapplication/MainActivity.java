package com.knight.myapplication;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDataset;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    private List<MyList> myList = new ArrayList<MyList>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots [i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount ; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer= new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


        populateMyList();

}

    private void populateMyList() {

        myList.add(new MyList("Ford",1940,R.mipmap.ic_local_offer_black_24dp,"Needing Work"));
        myList.add(new MyList("Ford",1940,R.drawable.tshirt,"Needing Work"));
        myList.add(new MyList("Ford",1940,R.mipmap.ic_laptop_black_24dp,"Needing Work"));
        myList.add(new MyList("Ford",1940,R.mipmap.ic_restaurant_menu_black_24dp,"Needing Work"));
        myList.add(new MyList("Ford",1940,R.mipmap.ic_library_books_black_24dp,"Needing Work"));
        myList.add(new MyList("Ford",1940,R.mipmap.ic_more_horiz_black_24dp,"Needing Work"));

    }



    public class MyTimerTask extends TimerTask {


    @Override
    public void run() {

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem() == 0) {

                    viewPager.setCurrentItem(1);
                } else if(viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(2);
                } else{
                    viewPager.setCurrentItem(0);
                }
            }
        });
    }
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.common_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    //@SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edu) {
            // Handle the camera action
        } else if (id == R.id.nav_electronics) {

        } else if (id == R.id.nav_rest) {

        } else if (id == R.id.nav_cloth) {

        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_offers) {
        } else if (id == R.id.nav_wallet) {

        } else if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_wishlist) {

        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_help) {
        } else if (id == R.id.nav_settings) {
        } else if (id == R.id.nav_logout) {
        } else if (id == R.id.nav_about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}