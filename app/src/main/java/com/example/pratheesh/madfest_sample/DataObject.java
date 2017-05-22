package com.example.pratheesh.madfest_sample;

import android.graphics.drawable.Drawable;

/**
 * Created by pratheesh on 10/23/2016.
 */
public class DataObject {

    private String image_url;
    private String details;
    private String id;
    private String profilepic;


    DataObject(String t1,String t2,String t3,String t4)
    {
        image_url=t1;
        details=t2;
        id=t3;
        profilepic=t4;
    }

    public String getImage() {
        return image_url;
    }

    public void setImage(String image_url)
    {
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getDetails()
    {
        return details;
    }

    public void setDetails(String details)
    {
        this.details = details;
    }
}
