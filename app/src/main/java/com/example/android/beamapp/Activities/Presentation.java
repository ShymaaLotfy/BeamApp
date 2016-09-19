package com.example.android.beamapp.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.beamapp.Fragments.PhotoFragment;
import com.example.android.beamapp.Fragments.QuestionFragment;
import com.example.android.beamapp.Model.DownloadTaskData;
import com.example.android.beamapp.Model.PhotoSlide;
import com.example.android.beamapp.Model.QuestionSlide;
import com.example.android.beamapp.Model.Slide;
import com.example.android.beamapp.Model.User;
import com.example.android.beamapp.Networking.GetDownloadTask;
import com.example.android.beamapp.Networking.Urls;
import com.example.android.beamapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class Presentation extends AppCompatActivity {

    User loggedInUser ;
    private ArrayList<Slide> slides = new ArrayList<>();
    SimpleFragmentPageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation);
        loggedInUser = (User) getIntent().getSerializableExtra("user");

        GetDownloadTask task = new GetDownloadTask(){

            @Override
            protected void onPostExecute(JSONObject object) {
                super.onPostExecute(object);
                try {

                    JSONObject data = object.getJSONObject("data");
                    JSONArray array = data.getJSONArray("slides");
                    for(int i =0 ; i<array.length();i++){
                        JSONObject current = array.getJSONObject(i);
                        int type = current.getInt("type");

                        if(type == 1){
                            slides.add(new PhotoSlide(current.getString("thumbnail_path")));
                            adapter.notifyDataSetChanged();


                        }else if(type == 2){

                            String question = current.getString("name");
                            ArrayList<String> ans = new ArrayList<>();

                            JSONArray Choices = current.getJSONArray("choices");
                            for (int j=0 ; j<Choices.length(); j++){
                                JSONObject a = Choices.getJSONObject(j);
                                ans.add(a.getString("content"));
                            }
                            slides.add(new QuestionSlide(question,ans));
                            adapter.notifyDataSetChanged();

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };
        task.execute(new DownloadTaskData(Urls.BASE_USER_PRES + loggedInUser.userID +Urls.REST_PRES +"130/",loggedInUser));

         //Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById( R.id.presentationViewPager);
        // Create an adapter that knows which fragment should be shown on each page
        adapter = new SimpleFragmentPageAdapter(getSupportFragmentManager());
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
            Bundle b = new Bundle();
            if (position >= 0) {

                if(slides.get(position) instanceof PhotoSlide){
                    f = new PhotoFragment();
                    b.putSerializable("pSlide",slides.get(position));
                    f.setArguments(b);

                }else if(slides.get(position)instanceof QuestionSlide){
                    f = new QuestionFragment();
                    b.putSerializable("qSlide",slides.get(position));
                    f.setArguments(b);
                }

            }
            return f;
        }

        @Override
        public int getCount() {
            return slides.size();
        }

    }
}
