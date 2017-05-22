package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 4/16/2017.
 */
public class Results_DataObject {

    private String round_name;
    private String opponent1_name;
    private String opponent1_image;
    private String opponent2_name;
    private String opponent2_image;
    private String winner;

    public  Results_DataObject(String t1,String t2,String t3,String t4,String t5, String t6)
    {
        round_name=t1;
        opponent1_name=t2;
        opponent1_image=t3;
        opponent2_name=t4;
        opponent2_image=t5;
        winner=t6;

    }

    public String getOpponent1_image() {
        return opponent1_image;
    }

    public void setOpponent1_image(String opponent1_image) {
        this.opponent1_image = opponent1_image;
    }

    public String getOpponent2_image() {
        return opponent2_image;
    }

    public void setOpponent2_image(String opponent2_image) {
        this.opponent2_image = opponent2_image;
    }

    public String getRound_name() {
        return round_name;
    }

    public void setRound_name(String round_name) {
        this.round_name = round_name;
    }

    public String getOpponent1_name() {
        return opponent1_name;
    }

    public void setOpponent1_name(String opponent1_name) {
        this.opponent1_name = opponent1_name;
    }

    public String getOpponent2_name() {
        return opponent2_name;
    }

    public void setOpponent2_name(String opponent2_name) {
        this.opponent2_name = opponent2_name;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
