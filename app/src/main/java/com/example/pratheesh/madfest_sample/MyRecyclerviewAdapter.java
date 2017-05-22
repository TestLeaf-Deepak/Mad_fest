package com.example.pratheesh.madfest_sample;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pratheesh on 10/23/2016.
 */
public class MyRecyclerviewAdapter extends RecyclerView.Adapter< RecyclerView.ViewHolder>{

    List<DataObject> dataObjects;
    Context c;
    private ImageLoader imageLoader,imageLoader1;

    Typeface  typeface;
    RecyclerView.ViewHolder dataObjectHolder;



    public  MyRecyclerviewAdapter(Context c,List<DataObject> d1)
    {
        this.c=c;
        this.dataObjects=d1;
    }

    @Override
    public int getItemViewType(int position) {

        DataObject dataObject=dataObjects.get(position);

        if(dataObject.getImage().equals("Null"))
        {
            Log.d("Null Image",dataObject.getImage());
            return 2;

        }
        else
        {
            Log.d("Image",dataObject.getImage()+dataObject.getDetails());
            return 1;

        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        switch (viewType)
        {

            case 1:

            View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_row, parent, false);
            dataObjectHolder = new DataObjectHolder1(view1);
                break;

            case 2:
             View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_row_no_image, parent, false);
             dataObjectHolder = new DataObjectHolder2(view2);
                break;

        }
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch(dataObjectHolder.getItemViewType()) {

            case 1:
            DataObjectHolder1 holder1 = (DataObjectHolder1)dataObjectHolder;
            DataObject dataObject = dataObjects.get(position);
          imageLoader = CustomVolleyRequest.getInstance(c).getImageLoader();
            //imageLoader.get(dataObject.getImage(), ImageLoader.getImageListener(holder1.image_details, R.drawable.images, android.R.drawable.ic_dialog_alert));
         // holder1.image_details.setImageUrl(dataObject.getImage(), imageLoader);
                boolean hasDrawable = (holder1.image_details.getDrawable() != null);
                if(hasDrawable) {
                    Log.d("loaded",dataObject.getImage());
                }
                else {
                    Log.d("not loaded",dataObject.getImage());
                }
              /* Picasso.with(this.c)
                        .load(dataObject.getImage()).placeholder(R.drawable.teams).transform(new BitmapTransform(60,100))
                       .into(holder1.image_details);*/

                Glide.with(this.c).load(dataObject.getImage()).into(holder1.image_details);


                imageLoader.get(dataObject.getProfilepic(), ImageLoader.getImageListener(holder1.profilepic, R.drawable.images, android.R.drawable.ic_dialog_alert));
            holder1.profilepic.setImageUrl(dataObject.getProfilepic(), imageLoader);
              //  Glide.with(this.c).load(dataObject.getProfilepic()).into(holder1.profilepic);
            holder1.details.setText(dataObject.getDetails());
            holder1.id.setText(dataObject.getId());
                break;
            case 2:
                DataObjectHolder2 holder2 = (DataObjectHolder2)dataObjectHolder;
                DataObject dataObject1 = dataObjects.get(position);
                imageLoader = CustomVolleyRequest.getInstance(c).getImageLoader();
                imageLoader.get(dataObject1.getProfilepic(), ImageLoader.getImageListener(holder2.profilepic, R.drawable.images, android.R.drawable.ic_dialog_alert));
                holder2.profilepic.setImageUrl(dataObject1.getProfilepic(), imageLoader);
                holder2.details.setText(dataObject1.getDetails());
                holder2.id.setText(dataObject1.getId());
                break;
        }

    }

    @Override
    public int getItemCount()
    {
        return dataObjects.size();
    }


    public static  class DataObjectHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView details,id;
        AspectRatioImageView profilepic;
      //FeedImageView image_details;
      ProportionalImageView image_details;
        Typeface  typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder1(View itemview)
        {
            super(itemview);
            details=(TextView)itemview.findViewById(R.id.cardrow_textview);
            details.setTypeface(typeface);
            id=(TextView)itemview.findViewById(R.id.cardrow_id);
            id.setTypeface(typeface);
           //image_details=(FeedImageView)itemview.findViewById(R.id.cardrow_image);
           image_details=(ProportionalImageView)itemview.findViewById(R.id.cardrow_image);
            profilepic=(AspectRatioImageView)itemview.findViewById(R.id.cardrow_profile);



        }

        @Override
        public void onClick(View v) {

        }
    }

    public static  class DataObjectHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView details,id;
        NetworkImageView profilepic;
        Typeface  typeface = Typeface.createFromAsset(itemView.getContext().getAssets(), "Fonts/OpenSans-Regular.ttf");

        public DataObjectHolder2(View itemview)
        {
            super(itemview);
            details=(TextView)itemview.findViewById(R.id.cardrow_textview);
            details.setTypeface(typeface);
            id=(TextView)itemview.findViewById(R.id.cardrow_id);
            id.setTypeface(typeface);
            profilepic=(NetworkImageView)itemview.findViewById(R.id.cardrow_profile);



        }

        @Override
        public void onClick(View v) {

        }
    }
}
