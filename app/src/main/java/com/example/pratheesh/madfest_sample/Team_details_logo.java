package com.example.pratheesh.madfest_sample;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class Team_details_logo extends Fragment {

    String teamname,teamimage,teammotto;
    FeedImageView imageView;
    ImageLoader imageLoader;
    Typeface typeface;
    TextView textView_teamname,textView_teammotto,textView_teamcaptain,textView_teamname_value,textView_teammotto_value,
            textView_teamcaptain_value;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view_discover= inflater.inflate(R.layout.activity_team_details_logo,container,false);
        return view_discover;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)

    {
        super.onActivityCreated(savedInstanceState);

        typeface= Typeface.createFromAsset(getActivity().getAssets(), "Fonts/OpenSans-Regular.ttf");
        imageView=(FeedImageView)getView().findViewById(R.id.teamdetails_image);
        textView_teamname=(TextView)getView().findViewById(R.id.teamdetails_teamname);
        textView_teamname.setTypeface(typeface);
        textView_teammotto=(TextView)getView().findViewById(R.id.teamdetails_teammotto);
        textView_teammotto.setTypeface(typeface);
        textView_teamcaptain=(TextView)getView().findViewById(R.id.teamdetails_teamcaptain);
        textView_teamcaptain.setTypeface(typeface);
        textView_teamname_value=(TextView)getView().findViewById(R.id.teamdetails_teamname_value);
        textView_teamname_value.setTypeface(typeface);
        textView_teammotto_value=(TextView)getView().findViewById(R.id.teamdetails_teammotto_value);
        textView_teammotto_value.setTypeface(typeface);
        textView_teamcaptain_value=(TextView)getView().findViewById(R.id.teamdetails_teamcaptain_value);
        textView_teamcaptain_value.setTypeface(typeface);


        Intent intent = getActivity().getIntent();
        if (null != intent) {
            teamname = intent.getStringExtra("username_intent");
            teamimage=intent.getStringExtra("teamimage_intent");
            teammotto=intent.getStringExtra("teammotto_intent");
            imageLoader = CustomVolleyRequest.getInstance(this.getActivity().getApplicationContext())
                    .getImageLoader();
            imageLoader.get(teamimage, ImageLoader.getImageListener
                    (imageView, R.drawable.images, android.R.drawable.ic_dialog_alert));
            imageView.setImageUrl(teamimage, imageLoader);
            textView_teamname_value.setText(teamname);
            textView_teamcaptain_value.setText("Deepak Nair");
            textView_teammotto_value.setText(teammotto);

        }
    }
}
