package com.sigma.springsecurity.web.controller;

import com.sigma.springsecurity.web.model.User;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tahir Ali Khan on 1/27/2016.
 */
@Controller
public class UsersController {

  @RequestMapping(value = { "/api/users" }, method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody public List<User> getUsers() {
    ArrayList<User> users = new ArrayList<User>();

    users.add(new User("tahir","tahir", (byte) 1));
    users.add(new User("ali","ali", (byte) 0));
    users.add(new User("sajid","sajid", (byte) 1));
    return users;
  }
}
