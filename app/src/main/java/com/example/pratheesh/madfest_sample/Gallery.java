package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class Gallery extends AppCompatActivity {


    private  ArrayList<ImageData> imageDatas;

    private Bitmap bitmap;

    String value_image;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private int PICK_IMAGE_REQUEST = 1;

    String formatdate,formatdate_image;

    private ProgressDialog loading;
    Typeface typeface;

    Toolbar toolbar;
    TextView txt_toolbar;

    public static final  String url="http://www.androidtesting.ml/madfest_select_gallery.php";
    String url2="http://www.androidtesting.ml/insert_madfest_add_gallery.php";


    String name,profilepic,teamname,username,mobilenumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        Intent intent=getIntent();
        if(null!=intent)
        {
            username = intent.getStringExtra("intent_username");
            name=intent.getStringExtra("intent_name");
            profilepic=intent.getStringExtra("intent_image");
            teamname=intent.getStringExtra("intent_teamname");
            mobilenumber=intent.getStringExtra("intent_mobilenumber");

        }

        toolbar=(Toolbar)findViewById(R.id.gallery_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_toolbar=(TextView)findViewById(R.id.gallery_toolbar_textview);
        txt_toolbar.setTypeface(typeface);

        imageDatas = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListner(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", imageDatas);
                bundle.putInt("position", position);


                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        getData();




    }




    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator=getMenuInflater();
        inflator.inflate(R.menu.gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.addimagegallery) {

            Intent intent = new Intent(Gallery.this, Add_Image.class);
            intent.putExtra("intent_username", username);
            intent.putExtra("intent_image", profilepic);
            intent.putExtra("intent_name", name);
            intent.putExtra("intent_teamname", teamname);
            intent.putExtra("intent_mobilenumber", mobilenumber);
            startActivity(intent);
        }


           //




        return super.onOptionsItemSelected(item);
    }





    private  void getData()
        {
            final ProgressDialog progressDialog=ProgressDialog.show(this,"Loading Data","Please Wait ....",false);
            StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("Testing", "Getting response");
                    Log.d("Testing",response);
                    progressDialog.dismiss();
                    try {
                        showJSON(response);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Gallery.this, e.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("Error",e.toString());
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    progressDialog.dismiss();
                    Log.d("Testing_Error",error.toString());

                }
            });

            MySingleton.getmInstance(Gallery.this).addRequestqueue(stringRequest);

        }

    private void showJSON(String response_data)
    {


        try
        {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data=null;
            imageDatas=new ArrayList<ImageData>();

            for(int i=0;i<result.length();i++)
            {

                ImageData imagedata=null;
                Log.d("Testing_again",i+"th time");
                data = result.getJSONObject(i);
                String img = data.getString("image");
                String id=data.getString("id");
                String timestamp=data.getString("timestamp");
                String detail=data.getString("name");
                Log.d("Testing_json", img + "and" +id );
                try {
                    imagedata = new ImageData(img,timestamp,detail,id);
                    imageDatas.add(i, imagedata);
                    Log.d("Testing_again", i + "th time");
                }
                catch (Exception e)
                {
                    Log.d("Testing_error",e.toString());
                }
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
            Log.d("Testing_error",e.toString());
        }

        Log.d("List", "Calling Function");

        adapter=new GalleryAdapter(this,imageDatas);
        recyclerView.setAdapter(adapter);

    }
}
