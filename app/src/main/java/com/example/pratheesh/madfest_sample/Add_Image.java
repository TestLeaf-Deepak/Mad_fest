package com.example.pratheesh.madfest_sample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_Image extends AppCompatActivity {

    String url2="http://www.androidtesting.ml/insert_madfest_add_gallery.php";


    String name,profilepic,teamname,username,mobilenumber;

    private int PICK_IMAGE_REQUEST = 1;

    private Bitmap bitmap;

    String formatdate,formatdate_image;

    private ProgressDialog loading;
    Typeface typeface;

    ImageView imageView;
    Button button;

    String value_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__image);
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

        imageView=(ImageView)findViewById(R.id.addimage_image);
        button=(Button)findViewById(R.id.addimage_add);
        button.setTypeface(typeface);

        showFileChooser();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                Date date = new Date();
                //Date timestamp= new Timestamp(date.getTime());
                formatdate = formatter.format(date);
                SimpleDateFormat formatter_image = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss", Locale.getDefault());
                Date date_image = new Date();
                //Date timestamp= new Timestamp(date.getTime());
                formatdate_image = formatter_image.format(date_image);
                Log.d("Testing", formatdate);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (value_image.isEmpty()) {
                    Toast.makeText(Add_Image.this, "Image is not selected properl pls try again", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("Image",value_image);
                    Log.d("Image",username+" "+name+" "+teamname+" "+formatdate_image);
                    loading = ProgressDialog.show(Add_Image.this, "Please wait...", "Adding the Image...", false, false);

                    Log.d("Picture", "selected");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Testing", response);
                            loading.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Add_Image.this);
                            builder.setMessage("Successfully added the Image")
                                    .setTitle("Register")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            //TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                            //textView.setTypeface(typeface);
                            dialog.show();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            loading.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Add_Image.this);
                            builder.setMessage("Error while adding the feed " + error.toString())
                                    .setTitle("Register")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            //TextView textView = (TextView) dialog.findViewById(android.R.id.message);
                            //textView.setTypeface(typeface);
                            dialog.show();


                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("id", username);
                            params.put("date", formatdate_image);
                            params.put("image", value_image);
                            params.put("name", name);
                            params.put("teamname", teamname);


                            return params;
                        }
                    };


                    MySingleton.getmInstance(Add_Image.this).addRequestqueue(stringRequest);
                }
            }

        });



    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {

        Log.d("Selected", "Image selected");
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
                imageView.setImageBitmap(bitmap);
                value_image=getStringImage(bitmap);
                Log.d("Selected","Got bmp image");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
