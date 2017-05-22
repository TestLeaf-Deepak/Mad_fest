package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class points_table extends Fragment {

    TextView t0,t1,t2,t3,t4,t5,t6,t7,t8;
    Typeface typeface;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    List<pointstable_DataObject> data_recyclerview;

    public static final  String url="http://www.androidtesting.ml/select_points_table.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.activity_points_table,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)

    {
        super.onActivityCreated(savedInstanceState);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        typeface=Typeface.createFromAsset(getActivity().getAssets(),"Fonts/OpenSans-Regular.ttf");
        t0=(TextView)getView().findViewById(R.id.TextView00);
        t0.setTypeface(typeface);
        t1=(TextView)getView().findViewById(R.id.TextView01);
        t1.setTypeface(typeface);
        t2=(TextView)getView().findViewById(R.id.TextView02);
        t2.setTypeface(typeface);
        t3=(TextView)getView().findViewById(R.id.TextView03);
        t3.setTypeface(typeface);
        t4=(TextView)getView().findViewById(R.id.TextView04);
        t4.setTypeface(typeface);
        t5=(TextView)getView().findViewById(R.id.TextView05);
        t5.setTypeface(typeface);
        t6=(TextView)getView().findViewById(R.id.TextView06);
        t6.setTypeface(typeface);
        t7=(TextView)getView().findViewById(R.id.TextView07);
        t7.setTypeface(typeface);
        t8=(TextView)getView().findViewById(R.id.TextView08);
        t8.setTypeface(typeface);


        recyclerView = (RecyclerView)getView(). findViewById(R.id.points_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    private  void getData()
    {
      //  final ProgressDialog progressDialog=ProgressDialog.show(this,"Loading Data","Please Wait ....",false);
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Testing", "Getting response");
                Log.d("Testing",response);
          //      progressDialog.dismiss();
                try {
                    showJSON(response);
                }
                catch (Exception e)
                {
                    Toast.makeText(points_table.this.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error",e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

              //  progressDialog.dismiss();
                Log.d("Testing_Error",error.toString());

            }
        });

        MySingleton.getmInstance(points_table.this.getActivity()).addRequestqueue(stringRequest);


    }

    private void showJSON(String response_data)
    {


        try
        {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data=null;
            data_recyclerview=new ArrayList<pointstable_DataObject>();

            for(int i=0;i<result.length();i++)
            {

                pointstable_DataObject dataObject=null;
                Log.d("Testing_again",i+"th time");
                data = result.getJSONObject(i);
                String event = data.getString("event");
                String location = data.getString("location");
                String team1=data.getString("team1");
                String team2=data.getString("team2");
                String team3=data.getString("team3");
                String team4=data.getString("team4");
                String team5=data.getString("team5");
                String team6=data.getString("team6");
                String team7=data.getString("team7");
                String team8=data.getString("team8");


                try {
                    dataObject = new pointstable_DataObject(event,location,team1,team2,team3,team4,team5,team6,team7,team8);
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

        Log.d("List", "Calling Function");
        // check(data_recyclerview);
        adapter=new Points_RecyclerViewAdapter(data_recyclerview,this.getActivity());
        recyclerView.setAdapter(adapter);

    }
}
