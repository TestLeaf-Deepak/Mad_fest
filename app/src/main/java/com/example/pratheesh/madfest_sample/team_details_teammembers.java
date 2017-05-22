package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class team_details_teammembers extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    List<Team_members_DataObject> data_recyclerview;
    String teamname;

    public static final  String url="http://www.androidtesting.ml/select_madfest_team_members.php";

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view_discover = inflater.inflate(R.layout.activity_team_details_teammembers, container, false);
        return view_discover;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)

    {
        super.onActivityCreated(savedInstanceState);

        Intent intent=getActivity().getIntent();
        if(null!=intent) {
            teamname = intent.getStringExtra("username_intent");

        }

        recyclerView = (RecyclerView)getView().findViewById(R.id.team_members_recycler_view);
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
                    Toast.makeText(team_details_teammembers.this.getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error",e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Log.d("Testing_Error",error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("teamname",teamname);
                return params;
            }
        };

        MySingleton.getmInstance(team_details_teammembers.this.getActivity()).addRequestqueue(stringRequest);


    }

    private void showJSON(String response_data) {


        try {
            JSONObject jsonObject = new JSONObject(response_data);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject data = null;
            data_recyclerview = new ArrayList<Team_members_DataObject>();

            for (int i = 0; i < result.length(); i++) {

                Team_members_DataObject dataObject = null;
                Log.d("Testing_again", i + "th time");
                data = result.getJSONObject(i);
                String image = data.getString("image");
                String name = data.getString("name");

                Log.d("Testing_json-", image + "-and-" + name);
                try {
                    dataObject = new Team_members_DataObject(name, image);
                    data_recyclerview.add(i, dataObject);
                    Log.d("Testing_again", i + "th time");
                } catch (Exception e) {
                    Log.d("Testing_error", e.toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Testing_error", e.toString());
        }

        Log.d("List", "Calling Function");
        // check(data_recyclerview);
        adapter = new Team_Members_RecyclerviewAdapter(data_recyclerview, this.getActivity());
        recyclerView.setAdapter(adapter);
    }
}
