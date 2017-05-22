package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_Feed extends AppCompatActivity implements  View.OnClickListener{

    Typeface typeface;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    EditText feed;
    ImageView feedimage;
    TextView browse;
    Button addfeed;
    int pictureselected=0;

    private long mLastClickTime = 0;

    String value_feedimage,value_feed;
   // String username;

    private ProgressDialog loading;

    String formatdate,formatdate_image;

    String name,profilepic,teamname,username,mobilenumber;

    String url="http://www.androidtesting.ml/insert_madfest_add_feed.php";
    String url_noimage="http://www.androidtesting.ml/insert_madfest_add_feed_No_Image.php";
    Toolbar toolbar;
    TextView txt_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__feed);

        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        feed=(EditText)findViewById(R.id.addfeed_edittext);
        feed.setTypeface(typeface);
        feedimage=(ImageView)findViewById(R.id.addfeed_image);
        browse=(TextView)findViewById(R.id.addfeed_browse);
        browse.setTypeface(typeface);
        addfeed=(Button)findViewById(R.id.addfeed_add);
        addfeed.setTypeface(typeface);

        browse.setOnClickListener(this);
        addfeed.setOnClickListener(this);

        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");


        txt_toolbar=(TextView)findViewById(R.id.toolbar_textview);
        toolbar=(Toolbar)findViewById(R.id.add_feed_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt_toolbar=(TextView)findViewById(R.id.toolbar_textview);
        txt_toolbar.setTypeface(typeface);

        Intent intent=getIntent();
        if(null!=intent)
        {
            username = intent.getStringExtra("intent_username");
            name=intent.getStringExtra("intent_name");
            profilepic=intent.getStringExtra("intent_image");
            teamname=intent.getStringExtra("intent_teamname");
            mobilenumber=intent.getStringExtra("intent_mobilenumber");

        }



    }


    public String getStringImage(Bitmap bmp){
        bitmap=Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    private void showFileChooser() {
        pictureselected=1;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                feedimage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        if(v==browse)
        {
            pictureselected=1;
            Log.d("Picture selected","True");
            showFileChooser();
            browse.setVisibility(View.INVISIBLE);

        }

        if(v==addfeed)
        {
            if(pictureselected==1)
            {
            value_feedimage=getStringImage(bitmap);
            }
            else
            {
                value_feedimage="No Image";
            }
            value_feed=feed.getText().toString();
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            Date date=new Date();
            //Date timestamp= new Timestamp(date.getTime());
            formatdate=formatter.format(date);
            SimpleDateFormat formatter_image=new SimpleDateFormat("yyyy-MM-dd HH_mm_ss", Locale.getDefault());
            Date date_image=new Date();
            //Date timestamp= new Timestamp(date.getTime());
            formatdate_image=formatter_image.format(date_image);
            Log.d("Testing",formatdate);
            loading = ProgressDialog.show(this, "Please wait...", "Adding the feed...", false, false);
            if(pictureselected==1)
            {
                Log.d("Picture","selected");

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response)
                            {

                                Log.d("Testing", response);
                                loading.dismiss();
                                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Feed.this);
                                builder.setMessage("Successfully added the feed")
                                       .setTitle("Register")
                                      .setPositiveButton(android.R.string.ok, null);
                                // AlertDialog dialog = builder.create();
                                //TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                                //textView.setTypeface(typeface);
                                //dialog.show();
                                pictureselected = 0;
                            }
                        }, new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                                loading.dismiss();
                                Log.d("Testing_response", error.toString());
                                AlertDialog.Builder builder = new AlertDialog.Builder(Add_Feed.this);
                                builder.setMessage("Error while adding the feed")
                                        .setTitle("Register")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                //TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                                //textView.setTypeface(typeface);
                                dialog.show();
                                pictureselected = 0;
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Log.d("Error","Timeout");
                                } else if (error instanceof AuthFailureError) {
                                    Log.d("Error","AuthFailure Error");
                                } else if (error instanceof ServerError) {
                                    Log.d("Error","Server Error");
                                } else if (error instanceof NetworkError) {
                                    Log.d("Error","Network error");
                                } else if (error instanceof ParseError) {
                                    Log.d("Error","Parse error");
                                }

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String, String> params = new HashMap<String, String>();
                                params.put("id", username);
                                params.put("feed", value_feed);
                                params.put("date", formatdate);
                                params.put("image", value_feedimage);
                                params.put("date_image", formatdate_image);
                                params.put("profilepic", profilepic);
                                params.put("name", name);
                                params.put("teamname", teamname);


                                return params;
                            }
                        };


                        MySingleton.getmInstance(Add_Feed.this).addRequestqueue(stringRequest);


            }






            else
            {
                Log.d("Picture","Not selected");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url_noimage, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Testing", response);
                        loading.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Feed.this);
                        builder.setMessage("Successfully added the feed")
                                .setTitle("Register")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                       // TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                        //textView.setTypeface(typeface);

                        pictureselected=0;
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Feed.this);
                        builder.setMessage("Error while adding the feed")
                                .setTitle("Register")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                       // TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                        //textView.setTypeface(typeface);
                        dialog.show();
                        pictureselected=0;

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id", username);
                        params.put("feed", value_feed);
                        params.put("date", formatdate);
                        params.put("image", value_feedimage);
                        params.put("date_image", formatdate_image);
                        params.put("profilepic", profilepic);
                        params.put("name", name);
                        params.put("teamname",teamname);


                        return params;
                    }
                };


                MySingleton.getmInstance(Add_Feed.this).addRequestqueue(stringRequest);
            }

        }

    }
}
