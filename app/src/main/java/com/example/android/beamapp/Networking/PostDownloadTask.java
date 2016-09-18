package com.example.android.beamapp.Networking;

import com.example.android.beamapp.Model.DownloadTaskData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class PostDownloadTask extends DownloadTask {

    @Override
    protected JSONObject doInBackground(DownloadTaskData... params) {

        JSONObject object = null ;

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(params[0].Url).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json ; charset=UTF-8");
            connection.connect();

            DataOutputStream wr = new DataOutputStream( connection.getOutputStream());

            JSONObject userInfo = new JSONObject();
            userInfo.put("email",params[0].User.userMail);
            userInfo.put("password", params[0].User.userPassword);

            wr.write( userInfo.toString().getBytes("UTF-8"));

            if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                if(jsonString != null){
                    object = new JSONObject(jsonString);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();} catch (JSONException e) {
            e.printStackTrace();
        }

        return object;
    }
}
