package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 4/9/2017.
 */
public class Rules_DataObject {

    private String number;
    private String details;

    public Rules_DataObject(String t1,String t2)
    {
        number=t1;
        details=t2;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
