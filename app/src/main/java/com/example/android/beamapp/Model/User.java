package com.example.android.beamapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class User implements Serializable{

    public final  String userMail ;
    public final  String userPassword ;
    public  ArrayList<String> presentationsTitles = new ArrayList<>();
    public  ArrayList<String> presentationsIds = new ArrayList<>();

    public User(String mail , String password){
        userMail = mail ;
        userPassword = password ;
    }

    public void setUserPresentations(String title , String id){

        presentationsTitles.add(title);
        presentationsIds.add(id);

    }
}
