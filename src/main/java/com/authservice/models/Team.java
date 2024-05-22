package com.authservice.models;



public class Team {


    private Integer id;

    private String nameTeam;

    public Team(){

    }

    public Team(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameTeam() {
        return nameTeam;
    }

    public void setNameTeam(String nameTeam) {
        this.nameTeam = nameTeam;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", nameTeam=" + nameTeam + "]";
    }
}
