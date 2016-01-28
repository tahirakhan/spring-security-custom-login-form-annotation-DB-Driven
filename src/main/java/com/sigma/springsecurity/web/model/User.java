package com.sigma.springsecurity.web.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Tahir Ali Khan on 1/27/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private long id;
  private String username;
  private String password;
  private byte enable;
  private String address;
  private String email;
  public User(){

  }
  public User(long id, String username, String address, String email){
    this.id = id;
    this.username = username;
    this.address = address;
    this.email = email;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
