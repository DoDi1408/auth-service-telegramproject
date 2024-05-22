package com.authservice.models;


public class Employee {

  private Integer id;

  private String firstName;

  private String lastName;

  private long telegramId;

  private String password;

  private Integer team;

  private String email;
  
  public Employee(){
  }

  public Employee(String firstName, String lastName, long telegramId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.telegramId = telegramId;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public long getTelegramId() {
    return telegramId;
  }

  public void setTelegramId(long telegramId) {
    this.telegramId = telegramId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getTeam() {
    return team;
  }

  public void setTeam(Integer team) {
    this.team = team;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  @Override
  public String toString() {
    return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telegramId=" + telegramId
        + ", password=" + password + "]";
  }
}