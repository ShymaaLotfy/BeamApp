package com.example.android.beamapp.Model;

import java.util.ArrayList;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class QuestionSlide  extends Slide {

    public String question ;
    public ArrayList<String> Answer;

    public QuestionSlide(String q , ArrayList<String> ans){

        question = q ;
        Answer = ans;
    }
}
