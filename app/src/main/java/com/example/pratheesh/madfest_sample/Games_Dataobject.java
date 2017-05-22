package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 12/19/2016.
 */
public class Games_Dataobject {

    private  String image;
    private  String name;
    private String description;

    public Games_Dataobject(String t1,String t2,String t3)
    {
        image=t1;
        name=t2;
        description=t3;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
