package com.example.restservice.accessingdatamongodb;

import org.springframework.data.annotation.Id;



public class CarrotUser {

  @Id
  public String id;

  public String email;

  public String password;

  public CarrotUser(String password, String email) {
    this.password = password;
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}