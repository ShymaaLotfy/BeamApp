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
public class GetDownloadTask extends DownloadTask {
    @Override
    protected JSONObject doInBackground(DownloadTaskData... params) {

        JSONObject object = null ;

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(params[0].Url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorize",params[0].User.token);
            connection.connect();


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
