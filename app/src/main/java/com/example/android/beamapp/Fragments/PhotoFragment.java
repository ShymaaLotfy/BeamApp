package com.example.android.beamapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.beamapp.Model.PhotoSlide;
import com.example.android.beamapp.Model.QuestionSlide;
import com.example.android.beamapp.Networking.Urls;
import com.example.android.beamapp.R;
import com.squareup.picasso.Picasso;

import java.net.URL;

/**
 * Created by Shimaa on 9/18/2016.
 */
public class PhotoFragment extends Fragment{

    PhotoSlide slide;
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        slide = (PhotoSlide) args.getSerializable("pSlide");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.photo_fragment, null);

        ImageView photoImage = (ImageView) content.findViewById(R.id.photoSlide);
        if(slide.imagePath != null ){
         Picasso.with(getContext()).load(Urls.BASE_IMAGE_URL + slide.imagePath).into(photoImage);}

        return content;

    }


}
