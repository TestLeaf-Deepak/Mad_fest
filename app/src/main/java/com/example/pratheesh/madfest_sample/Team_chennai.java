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
import android.support.v7.widget.Toolbar;
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

public class Team_chennai extends Fragment {

    Toolbar toolbar;
    TextView txt_toolbar;
    Typeface typeface;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    List<Team_Dataobject> data_recyclerview;

    public static final  String url="http://www.androidtesting.ml/select_madfest_team.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.activity_team_chennai,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)

    {
        super.onActivityCreated(savedInstanceState);
        typeface= Typeface.createFromAsset(getActivity().getAssets(), "Fonts/OpenSans-Regular.ttf");
        recyclerView = (RecyclerView)getView(). findViewById(R.id.teams_recycler_view);
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
                    Toast.makeText(Team_chennai.this.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
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

        MySingleton.getmInstance(Team_chennai.this.getActivity()).addRequestqueue(stringRequest);


    }

    private void showJSON(String response_data)
    {


        try
        {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data=null;
            data_recyclerview=new ArrayList<Team_Dataobject>();

            for(int i=0;i<result.length();i++)
            {

                Team_Dataobject dataObject=null;
                Log.d("Testing_again",i+"th time");
                data = result.getJSONObject(i);
                String image = data.getString("image");
                String name = data.getString("name");
                String description=data.getString("motto");

                Log.d("Testing_json-", image + "-and-" + name);
                try {
                    dataObject = new Team_Dataobject(name,description,image);
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
        adapter=new Team_RecyclerviewAdapter(data_recyclerview,this.getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerViewItemClickListner(this.getActivity(),new RecyclerViewItemClickListner.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        Intent i = new Intent(Team_chennai.this.getActivity(), TeamDetails.class);
                        i.putExtra("username_intent",data_recyclerview.get(position).getTeamname());
                        i.putExtra("teamimage_intent",data_recyclerview.get(position).getTeamlogo());
                        i.putExtra("teammotto_intent",data_recyclerview.get(position).getTeammotto());
                        startActivity(i);




                    }
                })
        );

    }
}
