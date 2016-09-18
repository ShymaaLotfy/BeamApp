package com.example.android.beamapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.beamapp.Model.DownloadTaskData;
import com.example.android.beamapp.Model.User;
import com.example.android.beamapp.Networking.DownloadTask;
import com.example.android.beamapp.Networking.PostDownloadTask;
import com.example.android.beamapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

   private EditText emailText ;
   private EditText passwordText ;
    private User loggedInUser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public  void logIn(View v) throws JSONException {

        emailText = (EditText)findViewById(R.id.mailText);
        passwordText = (EditText)findViewById(R.id.passwordText);

        String email = String.valueOf(emailText.getText());
        String password = String.valueOf(passwordText.getText());
        loggedInUser = new User("beam@beam.com", "testest");
        DownloadTaskData data = new DownloadTaskData("https://api.gobeam.me/api/authentications",loggedInUser );


        PostDownloadTask task = new PostDownloadTask(){
            @Override
            protected void onPostExecute(JSONObject obj) {
                super.onPostExecute(obj);

                if(obj != null){
                    Intent intent = new Intent(getApplicationContext(),ViewUserPresentations.class);
                    intent.putExtra("user",(Serializable)loggedInUser);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }


            }
        };
        task.execute(data);

    }

}
