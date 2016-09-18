package com.example.android.beamapp.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.beamapp.Model.DownloadTaskData;
import com.example.android.beamapp.Model.User;
import com.example.android.beamapp.Networking.DownloadTask;
import com.example.android.beamapp.Networking.GetDownloadTask;
import com.example.android.beamapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Shimaa on 9/15/2016.
 */
public class ViewUserPresentations extends AppCompatActivity {

    private User loggedInUser ;
    private ArrayList<String> presTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_user_presentations);
        loggedInUser = (User) getIntent().getSerializableExtra("user");

        GetDownloadTask task = new GetDownloadTask(){
            @Override
            protected void onPostExecute(JSONObject object) {
                super.onPostExecute(object);

                try {
                    JSONArray array = object.getJSONArray("data");
                    for(int i =0 ; i<array.length();i++){
                        JSONObject current = array.getJSONObject(i);
                        //presTitles.add(current.getString("title"));
                        loggedInUser.setUserPresentations(current.getString("title"),current.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        task.execute(new DownloadTaskData("https://api.gobeam.me/api/users/92/presentations/",loggedInUser));

        ListView listView = (ListView)findViewById(R.id.presList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,loggedInUser.presentationsTitles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                

            }
        });

    }


}
