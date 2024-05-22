package com.authservice.models;



public class Manager {


    private int id;

    private Team team;

    private Employee employee;

    public Manager(){
        this.employee = new Employee();
        this.team = new Team();
    }
    public Manager(Employee emp, Team team) {
        this.employee = emp;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setEmployee(Employee sEmployee){
        this.employee = sEmployee;
    }

    public Employee getEmployee(){
        return this.employee;
    }
    @Override
    public String toString() {
        return "Manager [id=" + id +", " + getEmployee().toString() + ", " + getTeam() + "]";
    }

    

}
