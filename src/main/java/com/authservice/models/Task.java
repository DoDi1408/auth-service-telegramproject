package com.authservice.models;

import java.util.Date;

public class Task {

    private Integer id;

    private Date startDate;

    private Date dueDate;

    private String description;

    private String title;

    private Integer stateTask;


    private Employee employee;

    public Task(){

    }

    public Task(Date startDate, Date dueDate, String title, String description, Employee employee) {
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.title = title;
        this.description = description;
        this.stateTask = 0;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStateTask() {
        return stateTask;
    }

    public void setStateTask(Integer stateTask) {
        this.stateTask = stateTask;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", startDate=" + startDate + ", dueDate=" + dueDate + ", description=" + description
                + ", stateTask=" + stateTask + ", employee=" + employee + "]";
    }
}
