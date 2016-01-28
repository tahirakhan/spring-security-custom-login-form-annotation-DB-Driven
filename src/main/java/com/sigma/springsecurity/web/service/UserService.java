package com.sigma.springsecurity.web.service;

import com.sigma.springsecurity.web.model.User;

import java.util.List;

/**
 * Created by Tahir Ali Khan on 1/28/2016.
 */
public interface UserService {

  User findById(long id);

  User findByName(String name);

  void saveUser(User user);

  void updateUser(User user);

  void deleteUserById(long id);

  List<User> findAllUsers();

  void deleteAllUsers();

  public boolean isUserExist(User user);

}
