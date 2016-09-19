package com.example.android.beamapp.Model;

import java.io.Serializable;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class Presentation implements Serializable {

    public String title;
    public String id;

    public Presentation(String title, String id){
        this.title = title;
        this.id = id;
    }

}
