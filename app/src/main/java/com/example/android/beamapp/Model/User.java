package com.example.android.beamapp.Model;

import com.example.android.beamapp.Activities.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class User implements Serializable{

    public final  String userMail ;
    public final  String userPassword ;
    public  String  userID;
    public String token;

    public ArrayList<Presentation> userPresentations = new ArrayList<>();
    public ArrayList<String> titles = new ArrayList<>();

    public User(String mail , String password){
        userMail = mail ;
        userPassword = password ;
    }

    public void setUserPresentations(String title , String id ){

        userPresentations.add(new Presentation(title,id));
        titles.add(title);
    }

    public ArrayList<String> getPresTitles(){
        ArrayList<String> titles = new ArrayList<>();

        for(int i=0 ; i< userPresentations.size() ; i++){
            titles.add(userPresentations.get(i).title);
        }

        return titles;
    }
}
