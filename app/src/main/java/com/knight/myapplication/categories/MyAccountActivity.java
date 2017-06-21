package com.knight.myapplication.categories;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.knight.myapplication.R;

/**
 * Created by knight on 6/15/2017.
 */

public class MyAccountActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
    }
}
