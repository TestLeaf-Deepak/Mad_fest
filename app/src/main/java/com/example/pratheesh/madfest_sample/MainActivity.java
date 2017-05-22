package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private long mLastClickTime = 0;


    EditText id,mailid,password,reenter_password,name,mobile;
    ImageView profilepic;
    Button browse,signup;
    String value_id,value_mailid,value_password,value_reenterpassword,value_profilepic,value_name,value_mobile,value_team;
    String item;
    Typeface typeface;
    private Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
    String response_text;
    String url="http://www.androidtesting.ml/insert_madfest_register.php";

    Toolbar toolbar;
    TextView txt_toolbar,teamname;

    Spinner spinner;
    TextInputLayout til_id,til_mailid,til_password,til_reenterpassword,til_name,til_mobile;

    private long mRequestStartTime;

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        typeface=Typeface.createFromAsset(getAssets(),"Fonts/OpenSans-Regular.ttf");

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Mankatha");
        categories.add("Bayanak Babas");
        categories.add("Force");
        categories.add("Morgan Minions");
        categories.add("Power of one");
        categories.add("Unstoppables");
        categories.add("Victorious Walkers");

        MySpinnerAdapter dataAdapter = new MySpinnerAdapter(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);



        name=(EditText)findViewById(R.id.main_edittext_name);
        name.setTypeface(typeface);

        mobile=(EditText)findViewById(R.id.main_edittext_mobile);
        mobile.setTypeface(typeface);

        id=(EditText)findViewById(R.id.main_edittext_capgid);
        id.setTypeface(typeface);

        mailid=(EditText)findViewById(R.id.main_edittext_capgemail);
        mailid.setTypeface(typeface);

       password=(EditText)findViewById(R.id.main_edittext_password);
        password.setTypeface(typeface);

        reenter_password=(EditText)findViewById(R.id.main_edittext_repassword);
        reenter_password.setTypeface(typeface);

        browse=(Button)findViewById(R.id.main_button_browse);
        browse.setTypeface(typeface);

        signup=(Button)findViewById(R.id.main_button_signup);
        signup.setTypeface(typeface);

        profilepic=(ImageView)findViewById(R.id.main_imageview_profofilepic);

        txt_toolbar=(TextView)findViewById(R.id.toolbar_textview_sign_up);
        toolbar=(Toolbar)findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txt_toolbar.setTypeface(typeface);

        teamname= (TextView)findViewById(R.id.text_view_teamname);
        teamname.setTypeface(typeface);

        til_id= (TextInputLayout)findViewById(R.id.text_input_layout_capgid);
        til_id.setTypeface(typeface);
        til_mailid=(TextInputLayout)findViewById(R.id.text_input_layout_mail);
        til_mailid.setTypeface(typeface);
        til_password=(TextInputLayout)findViewById(R.id.text_input_layout_password);
        til_password.setTypeface(typeface);
        til_reenterpassword=(TextInputLayout)findViewById(R.id.text_input_layout_reenterpassword);
        til_reenterpassword.setTypeface(typeface);

        til_name=(TextInputLayout)findViewById(R.id.text_input_layout_name);
        til_name.setTypeface(typeface);

        til_mobile=(TextInputLayout)findViewById(R.id.text_input_layout_mobile);
        til_mobile.setTypeface(typeface);


        browse.setOnClickListener(this);
        signup.setOnClickListener(this);


    }


    private void showFileChooser() {
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
                profilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();

        if(v==browse)
        {
            showFileChooser();
        }


        if(v==signup)
        {


            final ProgressDialog progressDialog=ProgressDialog.show(this,"Loading Data","Please Wait ....",false);
            value_name=name.getText().toString();
            value_mobile=mobile.getText().toString();
            value_team=item;
            value_id= id.getText().toString();
            value_mailid=mailid.getText().toString();
            value_password=password.getText().toString();
            value_reenterpassword=reenter_password.getText().toString();
            value_profilepic=getStringImage(bitmap);
            Log.d("Testing", value_team);

            mRequestStartTime = System.currentTimeMillis();

            StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("Testing", response);
                    progressDialog.dismiss();
                    long totalRequestTime = System.currentTimeMillis() - mRequestStartTime;
                    Log.d("Testing", String.valueOf(totalRequestTime));
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Successfully saved the Record")
                            .setTitle("Register")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
  //                  TextView textView=(TextView)dialog.findViewById(android.R.id.message);
//                    textView.setTypeface(typeface);
                    dialog.show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.d("Testing", error.toString());
                    progressDialog.dismiss();
                    long totalRequestTime = System.currentTimeMillis() - mRequestStartTime;
                    Log.d("Testing", String.valueOf(totalRequestTime));
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Error while saving the Record")
                            .setTitle("Register")
                            .setPositiveButton(android.R.string.ok, null);

                    AlertDialog dialog = builder.create();
  //                  TextView textView=(TextView)dialog.findViewById(android.R.id.message);
//                    textView.setTypeface(typeface);
                    dialog.show();


                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("id",value_id);
                    params.put("email",value_mailid);
                    params.put("password",value_password);
                    params.put("image",value_profilepic);
                    params.put("name",value_name);
                    params.put("mobile_number",value_mobile);
                    params.put("teamname",value_team);
                    return params;
                }
            };

            MySingleton.getmInstance(MainActivity.this).addRequestqueue(stringRequest);

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private static class MySpinnerAdapter extends ArrayAdapter<String> {
        // Initialise custom font, for example:
        Typeface font = Typeface.createFromAsset(getContext().getAssets(),"Fonts/OpenSans-Regular.ttf");

        // (In reality I used a manager which caches the Typeface objects)
        // Typeface font = FontManager.getInstance().getFont(getContext(), BLAMBOT);

        private MySpinnerAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

        // Affects default (closed) state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }

        // Affects opened state of the spinner
        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) super.getDropDownView(position, convertView, parent);
            view.setTypeface(font);
            return view;
        }
    }
}
