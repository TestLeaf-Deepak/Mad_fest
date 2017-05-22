package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 1/11/2017.
 */
public class Team_Dataobject {

    private String teamname;
    private String teammotto;
    private String teamlogo;

    public  Team_Dataobject(String t1,String t2,String t3)
    {
        teamname=t1;
        teammotto=t2;
        teamlogo=t3;
    }

    public String getTeamname() {
        return teamname;
    }

    public void setTeamname(String teamname) {
        this.teamname = teamname;
    }

    public String getTeammotto() {
        return teammotto;
    }

    public void setTeammotto(String teammotto) {
        this.teammotto = teammotto;
    }

    public String getTeamlogo() {
        return teamlogo;
    }

    public void setTeamlogo(String teamlogo) {
        this.teamlogo = teamlogo;
    }
}
