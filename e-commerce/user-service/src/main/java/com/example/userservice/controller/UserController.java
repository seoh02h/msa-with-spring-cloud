package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import com.example.userservice.vo.Greeting;
import com.example.userservice.vo.RequestUser;
import com.example.userservice.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

  private Environment env;

  private UserService userService;

  @Autowired
  public UserController(Environment env, UserService userService){
    this.userService = userService;
  }

//  private Environment env;
//
//  public UserController(Environment env) {
//    this.env = env;
//  } --> Greeting

  @Autowired
  private Greeting greeting;

  @GetMapping("/health_check")
  public String status(){
    return "It's working in user service";
  }

  @GetMapping("/welcome")
  public String welcome(){
//    return env.getProperty("greeting.message");
    return greeting.getMessage();
  }

  @PostMapping("/users")
  public ResponseEntity createUser(@RequestBody RequestUser user){
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    UserDto userDto = mapper.map(user, UserDto.class);
    userService.createUser(userDto);

    ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);

  }

}
