package com.example.android.beamapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.beamapp.R;

import java.util.List;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class Presentation extends AppCompatActivity {

    private List<String> urls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById( R.id.presentationViewPager);
        // Create an adapter that knows which fragment should be shown on each page
        SimpleFragmentPageAdapter adapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());
        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);
    }


    public class SimpleFragmentPageAdapter extends FragmentPagerAdapter {


        public SimpleFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

              Fragment f = null;
//            Bundle b = new Bundle();
            if (position >= 0) {
//                b.putString(urls.get(position),"url");
//                f.setArguments(b);
            }
            return f;
        }

        @Override
        public int getCount() {
            return urls.size();
        }

    }
}
