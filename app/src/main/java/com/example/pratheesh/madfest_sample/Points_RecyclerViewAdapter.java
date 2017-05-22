package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by pratheesh on 12/22/2016.
 */
public class Points_RecyclerViewAdapter extends RecyclerView.Adapter<Points_RecyclerViewAdapter.DataObjectHolder>{

    List<pointstable_DataObject> pointstable_dataObjects;
    Context c;
    
    public  Points_RecyclerViewAdapter(List<pointstable_DataObject> d,Context c1)
    {
        pointstable_dataObjects=d;
        c=c1;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_points_cardrow,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        pointstable_DataObject dataObject=pointstable_dataObjects.get(position);

        holder.e0.setText(dataObject.getEvent());
        holder.t1.setText(dataObject.getTeam1());
        holder.t2.setText(dataObject.getTeam2());
        holder.t3.setText(dataObject.getTeam3());
        holder.t4.setText(dataObject.getTeam4());
        holder.t5.setText(dataObject.getTeam5());
        holder.t6.setText(dataObject.getTeam6());
        holder.t7.setText(dataObject.getTeam7());
        holder.t8.setText(dataObject.getTeam8());


    }

    @Override
    public int getItemCount() {
        return  pointstable_dataObjects.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView e0,t1,t2,t3,t4,t5,t6,t7,t8;
        Typeface  typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview) {
            super(itemview);
           
            e0=(TextView)itemview.findViewById(R.id.card_row_00);
            e0.setTypeface(typeface);
            t1=(TextView)itemview.findViewById(R.id.card_row_01);
            t1.setTypeface(typeface);
            t2=(TextView)itemview.findViewById(R.id.card_row_02);
            t2.setTypeface(typeface);
            t3=(TextView)itemview.findViewById(R.id.card_row_03);
            t3.setTypeface(typeface);
            t4=(TextView)itemview.findViewById(R.id.card_row_04);
            t4.setTypeface(typeface);
            t5=(TextView)itemview.findViewById(R.id.card_row_05);
            t5.setTypeface(typeface);
            t6=(TextView)itemview.findViewById(R.id.card_row_06);
            t6.setTypeface(typeface);
            t7=(TextView)itemview.findViewById(R.id.card_row_07);
            t7.setTypeface(typeface);
            t8=(TextView)itemview.findViewById(R.id.card_row_08);
            t8.setTypeface(typeface);
        }

        @Override
        public void onClick(View v) {

        }


        }
    
}
