package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class voting_frame1 extends Fragment {

    Typeface typeface;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    List<Voting_BestTeam_DataObject> data_recyclerview;

    public static final  String url="http://www.androidtesting.ml/select_voting_best_team.php";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.activity_voting_frame1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)

    {

        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView)getView(). findViewById(R.id.voting_recyclerview_topteams);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    private  void getData()
    {
        final ProgressDialog progressDialog=ProgressDialog.show(this.getActivity(),"Loading Data","Please Wait ....",false);
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
                    Toast.makeText(voting_frame1.this.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
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

        MySingleton.getmInstance(voting_frame1.this.getActivity()).addRequestqueue(stringRequest);


    }



    private void showJSON(String response_data)
    {


        try
        {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data=null;
            data_recyclerview=new ArrayList<Voting_BestTeam_DataObject>();

            for(int i=0;i<result.length();i++)
            {

                Voting_BestTeam_DataObject dataObject=null;
                Log.d("Testing_again",i+"th time");
                data = result.getJSONObject(i);
                String image = data.getString("image");
                String name = data.getString("name");
                String description=data.getString("motto");

                Log.d("Testing_json-", image + "-and-" + name);
                try {
                    dataObject = new Voting_BestTeam_DataObject(name,description,image);
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
        adapter=new Voting_BestTeam_RecyclerviewAdapter(data_recyclerview,this.getActivity());
        recyclerView.setAdapter(adapter);


    }
}
