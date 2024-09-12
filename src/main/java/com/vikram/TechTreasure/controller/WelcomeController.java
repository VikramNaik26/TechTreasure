package com.vikram.TechTreasure.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * WelcomeController
 */
@RestController
@CrossOrigin
public class WelcomeController {

  @GetMapping
  public String welcome() {
    return "Welcome to TechTresure API";
  }
  
}
