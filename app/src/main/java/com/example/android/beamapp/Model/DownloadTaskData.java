package com.example.android.beamapp.Model;

import com.example.android.beamapp.Networking.DownloadTask;

import java.net.URL;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class DownloadTaskData {

    public String Url ;
    public User User;

    public DownloadTaskData (String url , User user){
        Url = url;
        User = user;
    }
}
