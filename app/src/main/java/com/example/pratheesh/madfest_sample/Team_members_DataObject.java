package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 1/31/2017.
 */
public class Team_members_DataObject {

    private String id;
    private String profilepic;

    public Team_members_DataObject(String i1,String i2)
    {
        id=i1;
        profilepic=i2;
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
}
