package com.example.pratheesh.madfest_sample;

/**
 * Created by pratheesh on 2/20/2017.
 */
public class Voting_BestTeam_DataObject {

    private String teamname;
    private String teammotto;

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

    private String teamlogo;

    public  Voting_BestTeam_DataObject(String t1,String t2,String t3)
    {
        teamname=t1;
        teammotto=t2;
        teamlogo=t3;
    }
}
