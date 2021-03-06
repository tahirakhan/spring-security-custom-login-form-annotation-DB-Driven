package com.sigma.springsecurity.web.controller;

import com.sigma.springsecurity.web.model.User;
import com.sigma.springsecurity.web.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

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

  @Autowired
  UserService userService;  //Service which will do all data retrieval/manipulation work


  //-------------------Retrieve All Users--------------------------------------------------------

  @RequestMapping(value = "/user/", method = RequestMethod.GET)
  public ResponseEntity<List<User>> listAllUsers() {
    List<User> users = userService.findAllUsers();
    if(users.isEmpty()){
      return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
    }
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
  }



  //-------------------Retrieve Single User--------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable("id") long id) {
    System.out.println("Fetching User with id " + id);
    User user = userService.findById(id);
    if (user == null) {
      System.out.println("User with id " + id + " not found");
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }


  class ReturnData{

    ReturnData(String message){
      this.message = message;

    }
    private String message;
    private HttpStatus code;


    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }

    public HttpStatus getCode() {
      return code;
    }

    public void setCode(HttpStatus code) {
      this.code = code;
    }
  }
  //-------------------Create a User--------------------------------------------------------

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<ReturnData> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
    System.out.println("Creating User " + user.getUsername());

    if (userService.isUserExist(user)) {
      System.out.println("A User with name " + user.getUsername() + " already exist");
      return new ResponseEntity<ReturnData>(new ReturnData("Test"),HttpStatus.CONFLICT);
    }
    if(user.getFirstName().equalsIgnoreCase("karamat")){
      System.out.println("A User with First name " + user.getFirstName() + " already exist");
      return new ResponseEntity<ReturnData>(new ReturnData("Frist Name "+user.getFirstName()+" is Duplicated"),HttpStatus.CONFLICT);
    }
    userService.saveUser(user);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
    return new ResponseEntity<ReturnData>(new ReturnData("Success"),headers, HttpStatus.CREATED);
  }



  //------------------- Update a User --------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
    System.out.println("Updating User " + id);

    User currentUser = userService.findById(id);

    if (currentUser==null) {
      System.out.println("User with id " + id + " not found");
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    currentUser.setUsername(user.getUsername());
    currentUser.setAddress(user.getAddress());
    currentUser.setEmail(user.getEmail());

    userService.updateUser(currentUser);
    return new ResponseEntity<User>(currentUser, HttpStatus.OK);
  }



  //------------------- Delete a User --------------------------------------------------------

  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
    System.out.println("Fetching & Deleting User with id " + id);

    User user = userService.findById(id);
    if (user == null) {
      System.out.println("Unable to delete. User with id " + id + " not found");
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    userService.deleteUserById(id);
    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }



  //------------------- Delete All Users --------------------------------------------------------

  @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteAllUsers() {
    System.out.println("Deleting All Users");

    userService.deleteAllUsers();
    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }
}
