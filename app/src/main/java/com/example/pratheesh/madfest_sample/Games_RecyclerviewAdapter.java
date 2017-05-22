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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by pratheesh on 12/19/2016.
 */
public class Games_RecyclerviewAdapter extends  RecyclerView.Adapter<Games_RecyclerviewAdapter.DataObjectHolder>{

    List<Games_Dataobject> games_dataobjects;
    Context c;
    private ImageLoader imageLoader;
    Typeface typeface;

    public  Games_RecyclerviewAdapter(List<Games_Dataobject> d,Context c1)
    {
        games_dataobjects=d;
        c=c1;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_games_chennai,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Games_Dataobject dataObject=games_dataobjects.get(position);
        imageLoader=CustomVolleyRequest.getInstance(c).getImageLoader();
        imageLoader.get(dataObject.getImage(), ImageLoader.getImageListener(holder.image, R.drawable.images, android.R.drawable.ic_dialog_alert));

       // holder.image.setImageUrl(dataObject.getImage(), imageLoader);

        Glide.with(this.c).load(dataObject.getImage()).into(holder.image);
        //imageLoader1=CustomVolleyRequest.getInstance(c).getImageLoader();

        holder.name.setText(dataObject.getName());
        holder.description.setText(dataObject.getDescription());

    }

    @Override
    public int getItemCount() {

        return games_dataobjects.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView name,description;
        //NetworkImageView image;
        CircularNetworkImageView image;
        Typeface  typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview)
        {
            super(itemview);

            name=(TextView)itemview.findViewById(R.id.games_name);
            name.setTypeface(typeface);
            description=(TextView)itemview.findViewById(R.id.games_description);
            description.setTypeface(typeface);
            image=(CircularNetworkImageView)itemview.findViewById(R.id.games_image);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
