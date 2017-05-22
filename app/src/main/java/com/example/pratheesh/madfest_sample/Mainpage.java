package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mainpage extends AppCompatActivity {


    Typeface typeface;

    TextView txt_username, txt_toolbar, addfeeds;
    //NetworkImageView imageView_profilepic;
    AspectRatioImageView imageView_profilepic;
    AspectRatioImageView imageView_profilepic1;

    ImageLoader imageLoader, imageLoader1;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    SwipeRefreshLayout mSwipeRefreshLayout;

    ArrayList<DataObject> data_recyclerview;

    String name, profilepic, teamname, username, mobilenumber;

    public static final String MY_PREFS_NAME = "madfest_login1";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    Toolbar toolbar;


    public static final String url = "http://www.androidtesting.ml/select_madfest_feeds.php";
    // public static final  String url2="http://www.androidtesting.ml/select_madfest_member_details.php";

    //ArrayList<String> user_details = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        typeface = Typeface.createFromAsset(getAssets(), "Fonts/OpenSans-Regular.ttf");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        toolbar = (Toolbar) findViewById(R.id.mainpage_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        //mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        txt_toolbar = (TextView) findViewById(R.id.toolbar_textview);
        txt_toolbar.setTypeface(typeface);
        addfeeds = (TextView) findViewById(R.id.mainpage_feeds);
        addfeeds.setTypeface(typeface);
        navigationView = (NavigationView) findViewById(R.id.navigationview);
        View header = navigationView.getHeaderView(0);
        txt_username = (TextView) header.findViewById(R.id.header_regnumber);
        txt_username.setTypeface(typeface);
        imageView_profilepic = (AspectRatioImageView) header.findViewById(R.id.mainpage_imageview_profile);
        imageView_profilepic1 = (AspectRatioImageView) findViewById(R.id.mainpage_imageview_profile1);
        setFontToMenuItem();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.menu_feeds) {
                    Toast.makeText(Mainpage.this, "Clicked on Add Feeds Tab", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, Add_Feed.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);
                }

                if (id == R.id.menu_gallery) {
                    Toast.makeText(Mainpage.this, "Clicked on Gallery Tab", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, Gallery.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);

                }

                if (id == R.id.menu_pointstable) {
                    Toast.makeText(Mainpage.this, "Clicked on points Table", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, Points_Table_ViewPager.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);
                }

                if (id == R.id.menu_games) {
                    Toast.makeText(Mainpage.this, "Clicked on Games", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, Games_ViewPager.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);
                }

                if (id == R.id.menu_voting) {
                    Toast.makeText(Mainpage.this, "Clicked on Games", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, voting_viewpager.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);
                }

                if (id == R.id.menu_teams) {
                    Toast.makeText(Mainpage.this, "Clicked on Teams", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Mainpage.this, Team_Viewpager.class);
                    intent.putExtra("intent_username", username);
                    intent.putExtra("intent_image", profilepic);
                    intent.putExtra("intent_name", name);
                    intent.putExtra("intent_teamname", teamname);
                    intent.putExtra("intent_mobilenumber", mobilenumber);
                    startActivity(intent);
                }

                return false;
            }
        });


        Intent intent = getIntent();
        if (null != intent) {
            username = intent.getStringExtra("intent_username");
            name = intent.getStringExtra("intent_name");
            profilepic = intent.getStringExtra("intent_image");
            teamname = intent.getStringExtra("intent_teamname");
            mobilenumber = intent.getStringExtra("intent_mobilenumber");

        }


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getData();
        // new TestAsync().execute();


        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(profilepic, ImageLoader.getImageListener
                (imageView_profilepic, R.drawable.images, android.R.drawable.ic_dialog_alert));
        imageView_profilepic.setImageUrl(profilepic, imageLoader);
       // Glide.with(this).load(profilepic).into(imageView_profilepic);
        //txt_username.setText(username);
        txt_username.setText(name);

       imageLoader1 = CustomVolleyRequest.getInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader1.get(profilepic, ImageLoader.getImageListener
                (imageView_profilepic1, R.drawable.images, android.R.drawable.ic_dialog_alert));
        imageView_profilepic1.setImageUrl(profilepic, imageLoader1);

       // Glide.with(this).load(profilepic).into(imageView_profilepic1);

      /*  mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh the data
                // Calls setRefreshing(false) when it is finish
                updateOperation();
            }
        });*/


    }

    private void updateOperation() {
        getData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.signout) {
            Toast.makeText(Mainpage.this, "SIGNING out", Toast.LENGTH_SHORT).show();
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.remove("username");
            editor.remove("name");
            editor.remove("image");
            editor.remove("teamname");
            editor.remove("mobilenum");
            editor.apply();
            Intent intent = new Intent(Mainpage.this, Login.class);
            startActivity(intent);


        }

        if (id == R.id.addimage) {
            Toast.makeText(Mainpage.this, "Moving to Gallery", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Mainpage.this, Gallery.class);
            intent.putExtra("intent_username", username);
            intent.putExtra("intent_image", profilepic);
            intent.putExtra("intent_name", name);
            intent.putExtra("intent_teamname", teamname);
            intent.putExtra("intent_mobilenumber", mobilenumber);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }








    private  void getData()
    {
        final ProgressDialog progressDialog=ProgressDialog.show(this,"Loading Data","Please Wait ....",false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>()
        {
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
                    Toast.makeText(Mainpage.this,e.toString(), Toast.LENGTH_SHORT).show();
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

              /*  stringRequest.setRetryPolicy(new RetryPolicy() {
                    @Override
                    public int getCurrentTimeout() {
                        return 50;
                    }

                    @Override
                    public int getCurrentRetryCount() {
                        return 50;
                    }

                    @Override
                    public void retry(VolleyError error) throws VolleyError {

                    }
                });*/

        MySingleton.getmInstance(Mainpage.this).addRequestqueue(stringRequest);


    }


    private void showJSON(String response_data)
    {


        try
        {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data=null;
            data_recyclerview=new ArrayList<DataObject>();

            for(int i=0;i<result.length();i++)
            {

                DataObject dataObject=null;
                Log.d("Testing_again",i+"th time");
                data = result.getJSONObject(i);
                String img = data.getString("image");
                String fd = data.getString("feed");
                String name=data.getString("name");
                String profilepic=data.getString("profilepic");
                Log.d("Testing_json", img + "and" + profilepic);
                try {
                    dataObject = new DataObject(img,fd,name,profilepic);
                    data_recyclerview.add(i, dataObject);
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

        Log.d("List","Calling Function");
        check(data_recyclerview);
        adapter=new MyRecyclerviewAdapter(this,data_recyclerview);
        recyclerView.setAdapter(adapter);

    }

    private void check(ArrayList<DataObject> test)
    {
        Log.d("Testing","In function");
        for (DataObject b:test
             ) {
            Log.d("List","In Loop");
            Log.d("List",b.getDetails()+b.getImage());

        }
    }

    private void parseData(JSONArray array)
    {

        for(int i=0;i<array.length();i++)
        {

            JSONObject jsonObject=null;
            try
            {
                DataObject dataObject=null;
                jsonObject=array.getJSONObject(i);
                dataObject.setImage(jsonObject.getString("image"));
                dataObject.setDetails(jsonObject.getString("feed"));
                Log.d("Testing", jsonObject.getString("image") + jsonObject.getString("feed"));
                data_recyclerview.add(dataObject);
            }
            catch (Exception e)
            {

            }


        }

       // adapter=new MyRecyclerviewAdapter(this,data_recyclerview);
        //recyclerView.setAdapter(adapter);


    }
    private void setFontToMenuItem()

    {
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }

                applyFontToMenuItem(mi);
            }

            //the method we have create in activity

        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
}
