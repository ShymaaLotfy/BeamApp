package com.example.android.beamapp.Networking;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Shimaa on 9/15/2016.
 */
public class DownloadTask extends AsyncTask<JSONObject, Void, Boolean> {

    private static final String  URL = "https://api.gobeam.me/api/authentications" ;

    @Override
    protected Boolean doInBackground(JSONObject... params) {

        boolean response = false ;

        try {
            HttpsURLConnection connection = (HttpsURLConnection) new URL(URL).openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json ; charset=UTF-8");
            connection.connect();

            DataOutputStream wr = new DataOutputStream( connection.getOutputStream());
                wr.write( params[0].toString().getBytes("UTF-8"));

            if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK){
                String jsonString = readResponse(connection.getInputStream());
                if(jsonString != null){
                    response = true;
                }
                JSONObject object = new JSONObject(jsonString);
            }

        } catch (IOException e) {
            e.printStackTrace();} catch (JSONException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String readResponse(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        StringBuilder builder = new StringBuilder();
        while(true) {
            if(line == null) break;
            builder.append(line);
            line = reader.readLine();
        }

        String jSONString = builder.toString();

        return jSONString;
    }

}