package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by pratheesh on 1/11/2017.
 */


public class Team_RecyclerviewAdapter extends  RecyclerView.Adapter<Team_RecyclerviewAdapter.DataObjectHolder>{


    List<Team_Dataobject> teams_dataobjects;
    Context c;
    private ImageLoader imageLoader;
    Typeface typeface;

    public  Team_RecyclerviewAdapter(List<Team_Dataobject> d,Context c1)
    {
        teams_dataobjects=d;
        c=c1;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teams_cardrow,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Team_Dataobject dataObject=teams_dataobjects.get(position);
        imageLoader=CustomVolleyRequest.getInstance(c).getImageLoader();
        imageLoader.get(dataObject.getTeamlogo(), ImageLoader.getImageListener(holder.image, R.drawable.images, android.R.drawable.ic_dialog_alert));

        holder.image.setImageUrl(dataObject.getTeamlogo(), imageLoader);

        //imageLoader1=CustomVolleyRequest.getInstance(c).getImageLoader();

        holder.name.setText(dataObject.getTeamname());
        holder.description.setText(dataObject.getTeammotto());

    }

    @Override
    public int getItemCount() {
        return teams_dataobjects.size();
    }


    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView name,description;
       // NetworkImageView image;
        CircularNetworkImageView image;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview)
        {
            super(itemview);

            name=(TextView)itemview.findViewById(R.id.teams_name);
            name.setTypeface(typeface);
            description=(TextView)itemview.findViewById(R.id.teams_description);
            description.setTypeface(typeface);
            image=(CircularNetworkImageView)itemview.findViewById(R.id.teams_image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
