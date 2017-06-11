package com.knight.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by knight on 6/11/2017.
 */

public class SliderAdapter extends PagerAdapter {

    private int[] image_resources = {

            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3,

};

private Context ctx;

private LayoutInflater layoutInflator;


public SliderAdapter(Context ctx) {
        this.ctx = ctx;
}

@Override
public int getCount(){
        return image_resources.length;
}

@Override
public boolean isViewFromObject (View view, Object object){

        return (view == (LinearLayout) object);
        }

@Override
public Object instantiateItem(ViewGroup container, int position) {

        layoutInflator = (LayoutInflater) ctx.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflator.inflate(R.layout.imageviews, container, false);

        ImageView imageView = (ImageView)item_view.findViewById(R.id.slider_image);
        imageView.setImageResource (image_resources[position]);
        container.addView(item_view);

        return item_view;
        }

@Override
public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
        }
}
