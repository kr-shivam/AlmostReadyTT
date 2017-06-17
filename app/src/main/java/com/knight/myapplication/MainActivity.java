package com.knight.myapplication;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    int[] mallicon = {R.drawable.mall_pheonix, R.drawable.mall_ub, R.drawable.mall_pheonix, R.drawable.mall_ub, R.drawable.mall_pheonix};
    String[] titles = {"Pheonix Mall", "UB Mall", "Pheonix Mall", "UB Mall", "Pheonix Mall"};
    int[] ratings = {2, 4, 4, 1, 5};
    int[] num_ratings = {34, 56, 65, 354, 67};
    String[] distance = {"3.5 km", "5 km", "7 km", "8.2 km", "4 km"};
    int i;

    private List<MyList> myList = new ArrayList<MyList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);


        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);


        populateMyList();


        ListView listView = (ListView) findViewById(R.id.myListView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

        @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

            if (position == 0){
                Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
                startActivity(myintent);
            }
            if(position == 1){
                Intent myintent = new Intent(view.getContext(),MallUBActivity.class);
                startActivityForResult(myintent,1);
            }
            if(position == 2){
                Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
                startActivityForResult(myintent,2);
            }
            if(position == 3){
                Intent myintent = new Intent(view.getContext(),MallUBActivity.class);
                startActivityForResult(myintent,3);
            }
            if(position == 4){
                Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
                startActivityForResult(myintent,4);
            }
        }

        });


    }

    class CustomAdapter extends BaseAdapter {


        @Override
        public int getCount() {

            return mallicon.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.row, null);
            ImageView ivMallIcon = (ImageView) view.findViewById(R.id.ivMallIcon);
            TextView tvMallTitle = (TextView) view.findViewById(R.id.tvMallTitle);
            RatingBar rbMallRating = (RatingBar) view.findViewById(R.id.rbMallRating);
            TextView tvNumOfRatings = (TextView) view.findViewById(R.id.number_rating);
            TextView tvMallDistance = (TextView) view.findViewById(R.id.tvMallDistance);

            ivMallIcon.setImageResource(mallicon[position]);
            tvMallTitle.setText(titles[position]);
            rbMallRating.setNumStars(ratings[position]);
            tvNumOfRatings.setText(String.valueOf((num_ratings[position])));
            tvMallDistance.setText(distance[position]);


            return view;
        }
    }

    private void populateMyList() {

        myList.add(new MyList(R.mipmap.ic_local_offer_black_24dp));
        myList.add(new MyList(R.drawable.tshirt));
        myList.add(new MyList(R.mipmap.ic_laptop_black_24dp));
        myList.add(new MyList(R.mipmap.ic_restaurant_menu_black_24dp));
        myList.add(new MyList(R.mipmap.ic_library_books_black_24dp));
        myList.add(new MyList(R.mipmap.ic_more_horiz_black_24dp));

    }
   // @Override
    //ImageView image_offer  = (ImageView) view.findViewById(R.id.offer_icon);
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){

        if (position == 0) {
            Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
            startActivity(myintent);
        }
        if(position == 1){
            Intent myintent = new Intent(view.getContext(),MallUBActivity.class);
            startActivityForResult(myintent,1);
        }
        if(position == 2){
            Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
            startActivityForResult(myintent,2);
        }
        if(position == 3){
            Intent myintent = new Intent(view.getContext(),MallUBActivity.class);
            startActivityForResult(myintent,3);
        }
        if(position == 4){
            Intent myintent = new Intent(view.getContext(),MallPheonixActivity.class);
            startActivityForResult(myintent,4);
        }
    }


    public class MyTimerTask extends TimerTask {


        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {

                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.location_grab){

            Intent intent = new Intent(this, LocationGrabActivity.class);
            startActivity(intent);

        }

        if(id == R.id.notification_icon){

            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.common_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //@SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edu) {
            Intent intent = new Intent(this, EducationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_electronics) {

            Intent intent = new Intent(this, ElectronicsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rest) {

            Intent intent = new Intent(this, RestaurantsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cloth) {

            Intent intent = new Intent(this, ApparelsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_notifications) {

            Intent intent = new Intent(this, NotificationsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_offers) {

            Intent intent = new Intent(this, OffersActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_wallet) {

            Intent intent = new Intent(this, WalletActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_wishlist) {

            Intent intent = new Intent(this, FavouritesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_account) {

            Intent intent = new Intent(this, MyAccountActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_help) {

            Intent intent = new Intent(this, HelpCenterActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logout) {

            //INSERT LOGOUT FUNCTION

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
