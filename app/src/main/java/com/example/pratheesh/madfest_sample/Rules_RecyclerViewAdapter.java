package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by pratheesh on 4/9/2017.
 */
public class Rules_RecyclerViewAdapter extends  RecyclerView.Adapter<Rules_RecyclerViewAdapter.DataObjectHolder>{

    List<Rules_DataObject> rules_dataObjects;
    Context c;

    public Rules_RecyclerViewAdapter(List<Rules_DataObject> d1,Context c1)
    {
        rules_dataObjects=d1;
        c=c1;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rules,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        Rules_DataObject dataObject=rules_dataObjects.get(position);
        holder.number.setText(dataObject.getNumber());
        holder.rules.setText(dataObject.getNumber());

    }

    @Override
    public int getItemCount() {
        return rules_dataObjects.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView number,rules;
        Typeface typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder(View itemview) {
            super(itemview);
            number=(TextView)itemview.findViewById(R.id.rules_number);
            rules=(TextView)itemview.findViewById(R.id.rules_rule);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
