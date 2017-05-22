package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements  View.OnClickListener{

    Typeface typeface;

    EditText username,password;
    Button login;
    String username_value,password_value;
    String username_value_get,password_value_get,image_value_get,name_value_get,teamname_value_get,mobilenum_value_get;

    TextView signup;

    public static final String MY_PREFS_NAME = "madfest_login1";


    //Config values

    public static final String DATA_URL = "http://www.androidtesting.ml/select_madfest_register.php";
    public static final String KEY_ID = "id";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_IMAGE = "image";
    public static final String JSON_ARRAY = "result";

    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        username=(EditText)findViewById(R.id.edittext_login_username);
        username.setTypeface(typeface);

        password=(EditText)findViewById(R.id.edittext_login_password);
        password.setTypeface(typeface);

        login=(Button)findViewById(R.id.button_login_login);
        login.setTypeface(typeface);

        signup=(TextView)findViewById(R.id.login_signup);
        signup.setTypeface(typeface);

        signup.setOnClickListener(this);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);

        String shared_username = prefs.getString("username", null);//"No name defined" is the default value.
        String shared_name = prefs.getString("name",null);
        String shared_image = prefs.getString("image",null);
        String shared_teamname = prefs.getString("teamname",null);
        String shared_mobilenumber = prefs.getString("mobilenum",null);//0 is the default value.

        if(shared_username!=null && shared_name!=null&&shared_image!=null &&shared_teamname!=null&&shared_mobilenumber!=null) {
            Intent intent=new Intent(Login.this,Mainpage.class);
            intent.putExtra("intent_username", shared_username);

            intent.putExtra("intent_name", shared_name);
            intent.putExtra("intent_teamname", shared_teamname);
            intent.putExtra("intent_mobilenumber",shared_mobilenumber);
            intent.putExtra("intent_image", shared_image);
            startActivity(intent);
        }
        else
        {
            login.setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {

        if(v==login)
        {
            username_value=username.getText().toString();
            password_value=password.getText().toString();

            loading = ProgressDialog.show(this, "Please wait...", "Fetching...", false, false);

            StringRequest stringRequest=new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    loading.dismiss();
                    Log.d("Testing", response);
                    showJSON(response);
                    if(username_value.equalsIgnoreCase(username_value_get) && password_value.equals(password_value_get))
                    {

                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("username", username_value_get);
                        editor.putString("name",name_value_get);
                        editor.putString("image",image_value_get);
                        editor.putString("teamname", teamname_value_get);
                        editor.putString("mobilenum",mobilenum_value_get);

                        editor.commit();
                        Intent intent=new Intent(Login.this,Mainpage.class);
                        intent.putExtra("intent_username", username_value_get);
                        intent.putExtra("intent_image", image_value_get);
                        intent.putExtra("intent_name", name_value_get);
                        intent.putExtra("intent_teamname", teamname_value_get);
                        intent.putExtra("intent_mobilenumber",mobilenum_value_get);
                        startActivity(intent);
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    try {

                        Toast.makeText(Login.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(Login.this, "Network Error", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }

                }
            })

            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id",username_value);
                    params.put("password",password_value);


                    return params;
                }
            };

            MySingleton.getmInstance(Login.this).addRequestqueue(stringRequest);
            }

        if(v==signup)
        {
            Intent intent=new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }

        }

    private void showJSON(String response)
    {

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject data = result.getJSONObject(0);
            username_value_get = data.getString(KEY_ID);
            password_value_get = data.getString(KEY_PASSWORD);
            image_value_get = data.getString(KEY_IMAGE);
            name_value_get=data.getString("name");
            teamname_value_get=data.getString("teamname");
            mobilenum_value_get=data.getString("mobile_number");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
