package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by pratheesh on 4/16/2017.
 */
public class Results_RecyclerViewAdapter extends RecyclerView.Adapter<Results_RecyclerViewAdapter.DataObjectHolder>{


    List<Results_DataObject> results_dataobjects;
    Context c;
    private ImageLoader imageLoader;
    Typeface typeface;

    public  Results_RecyclerViewAdapter(List<Results_DataObject> d,Context c1)
    {
        results_dataobjects=d;
        c=c1;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_row_results,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Results_DataObject dataObject=results_dataobjects.get(position);
        holder.round_name.setText(dataObject.getRound_name());
        holder.opponent1_name.setText(dataObject.getOpponent1_name());
        holder.opponent2_name.setText(dataObject.getOpponent2_name());
        holder.opponent_winner.setText(dataObject.getWinner());
        Glide.with(this.c).load(dataObject.getOpponent1_image()).into(holder.opponent_1_image);
        Glide.with(this.c).load(dataObject.getOpponent2_image()).into(holder.opponent_2_image);


    }

    @Override
    public int getItemCount() {
        return results_dataobjects.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView opponent1_name,opponent2_name,opponent_winner,round_name;
        // NetworkImageView image;
        CircularNetworkImageView opponent_1_image,opponent_2_image;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview)
        {
            super(itemview);

            round_name=(TextView)itemview.findViewById(R.id.round_name);
            opponent1_name =(TextView)itemview.findViewById(R.id.opponent1_name);
            opponent2_name=(TextView)itemview.findViewById(R.id.opponent2_name);
            opponent_winner=(TextView)itemview.findViewById(R.id.opponent_winner_name);
            opponent_1_image=(CircularNetworkImageView)itemview.findViewById(R.id.opponent1_image);
            opponent_2_image=(CircularNetworkImageView)itemview.findViewById(R.id.opponent2_image);



        }

        @Override
        public void onClick(View v) {

        }
    }
}
