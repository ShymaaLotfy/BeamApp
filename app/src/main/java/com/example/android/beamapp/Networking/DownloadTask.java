package com.example.android.beamapp.Networking;

import android.os.AsyncTask;

import com.example.android.beamapp.Model.DownloadTaskData;

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
public abstract class DownloadTask extends AsyncTask<DownloadTaskData, Void, JSONObject> {

    @Override
    protected abstract JSONObject doInBackground(DownloadTaskData... params);

    protected String readResponse(InputStream inputStream) throws IOException {
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