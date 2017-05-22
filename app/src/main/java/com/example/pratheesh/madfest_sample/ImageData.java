package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 11/17/2016.
 */
public class ImageData {

    private String imageurl;
    private String imagetimestamp;
    private String imagedetails;
    private String imageid;

    public  ImageData(String u1,String t1,String d1,String i1)

    {
        this.imageurl=u1;
        this.imagetimestamp=t1;
        this.imagedetails=d1;
        this.imageid=i1;

    }

    public String getImagetimestamp() {
        return imagetimestamp;
    }

    public void setImagetimestamp(String imagetimestamp) {
        this.imagetimestamp = imagetimestamp;
    }

    public String getImagedetails() {
        return imagedetails;
    }

    public void setImagedetails(String imagedetails) {
        this.imagedetails = imagedetails;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getImageurl() {
        return imageurl;

    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
