package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/first-service")
public class FirstServiceController {

  @GetMapping("/welcome")
  public String welcome(){
    return "First Service";
  }

  @GetMapping("/message")
  public String message(@RequestHeader("first-request") String header){
    log.info(header);
    return "First Service Message";
  }

  @GetMapping("/check")
  public String check(){
    return "First Service Check";
  }

}
