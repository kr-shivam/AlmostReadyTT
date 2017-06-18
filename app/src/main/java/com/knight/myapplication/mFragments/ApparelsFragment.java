package com.knight.myapplication.mFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.knight.myapplication.R;
import com.knight.myapplication.mData.MallList;
import com.knight.myapplication.mListView.CustomAdapter;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by knight on 6/18/2017.
 */

public class ApparelsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_apparels,container, false);
        ListView lv = (ListView) rootView.findViewById(R.id.apparelsListView);
        CustomAdapter adapter = new CustomAdapter(this.getActivity(),getApparelsMall());
        lv.setAdapter(adapter);

        return rootView;
    }


    private ArrayList<MallList> getApparelsMall(){

        ArrayList<MallList> malls = new ArrayList<>();

        MallList mallList;
        mallList = new MallList (R.drawable.mall_pheonix, "Pheonix Mall", "6 km");
        malls.add(mallList);

        mallList = new MallList(R.drawable.mall_pheonix, "Pheonix Mall2", "7.5 km");
        malls.add(mallList);

        mallList = new MallList(R.drawable.mall_ub, "UB Mall", "7.0 km");
        malls.add(mallList);

        mallList = new MallList(R.drawable.mall_ub, "UB Mall2", "7.5 km");
        malls.add(mallList);

        return malls;

    }

    @Override
    public String toString() {

        String title = "Apparels";
        return title;
    }
}
