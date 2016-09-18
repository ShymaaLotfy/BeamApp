package com.example.android.beamapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.beamapp.Networking.DownloadTask;
import com.example.android.beamapp.R;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
   private EditText emailText ;
   private EditText passwordText ;


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

        JSONObject userInfo = new JSONObject();
        userInfo.put("email",email);
        userInfo.put("password", password);

        DownloadTask task = new DownloadTask(){
            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);

                if(aBoolean == true){
                    Intent intent = new Intent(getApplicationContext(),ViewOrAddPresentation.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        };
        task.execute(userInfo);

    }

}
