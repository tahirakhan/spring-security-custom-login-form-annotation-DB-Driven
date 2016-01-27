package com.sigma.springsecurity.web.model;

/**
 * Created by Tahir Ali Khan on 1/27/2016.
 */
public class User {

  private String username;
  private String password;
  private byte enable;

  public User(){

  }
  public User(String username,String password, byte enable){
    super();
    this.username = username;
    this.password = password;
    this.enable = enable;
  }
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public byte getEnable() {
    return enable;
  }

  public void setEnable(byte enable) {
    this.enable = enable;
  }
}
