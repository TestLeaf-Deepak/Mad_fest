package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by pratheesh on 1/31/2017.
 */
public class Team_Members_RecyclerviewAdapter extends  RecyclerView.Adapter<Team_Members_RecyclerviewAdapter.DataObjectHolder>{

    List<Team_members_DataObject> team_members_dataobjects;
    Context c;
    private ImageLoader imageLoader;
    Typeface typeface;

    public Team_Members_RecyclerviewAdapter(List<Team_members_DataObject> a,Context b)
    {
        team_members_dataobjects=a;
        c=b;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.team_members_cardrow,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Team_members_DataObject dataObject=team_members_dataobjects.get(position);
        imageLoader=CustomVolleyRequest.getInstance(c).getImageLoader();
        imageLoader.get(dataObject.getProfilepic(), ImageLoader.getImageListener(holder.image, R.drawable.images, android.R.drawable.ic_dialog_alert));

        holder.image.setImageUrl(dataObject.getProfilepic(), imageLoader);

        //imageLoader1=CustomVolleyRequest.getInstance(c).getImageLoader();

        holder.name.setText(dataObject.getId());


    }

    @Override
    public int getItemCount() {
        return team_members_dataobjects.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        // NetworkImageView image;
        CircularNetworkImageView image;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview) {
            super(itemview);

            name=(TextView)itemview.findViewById(R.id.team_members_name);
            name.setTypeface(typeface);
            image=(CircularNetworkImageView)itemview.findViewById(R.id.team_members_image);

        }
        @Override
        public void onClick(View v) {

        }
    }
}
