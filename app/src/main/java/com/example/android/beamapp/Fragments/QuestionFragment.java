package com.example.android.beamapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.beamapp.Model.QuestionSlide;
import com.example.android.beamapp.R;


/**
 * Created by Shimaa on 9/18/2016.
 */
public class QuestionFragment extends Fragment {

    QuestionSlide slide;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        slide = (QuestionSlide) args.getSerializable("qSlide");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.question_fragment, null);

        TextView question = (TextView)content.findViewById(R.id.Question);
        TextView firstAns = (TextView)content.findViewById(R.id.firstAnswer);
        TextView secAns = (TextView)content.findViewById(R.id.secondAnswer);
        TextView thirdAns = (TextView)content.findViewById(R.id.thirdAnswer);

        question.setText(slide.question);
        firstAns.setText(slide.Answer.get(0));
        secAns.setText(slide.Answer.get(1));
        //thirdAns.setText(slide.Answer.get(2));

        return content;

    }
}
