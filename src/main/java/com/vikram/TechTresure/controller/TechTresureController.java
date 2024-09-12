package com.vikram.TechTresure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.TechTresure.constants.TechTresureConstants;
import com.vikram.TechTresure.service.TechTresureService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * TechTresureController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/tech-tresure")
public class TechTresureController {

  private TechTresureService techTresureService;

  public TechTresureController(TechTresureService techTresureService) {
    this.techTresureService = techTresureService;
  }

  @GetMapping
  public ResponseEntity<?> TechTresure(@RequestParam(required = false) String password) {
    if(password == null || password.isEmpty()) {
      return new ResponseEntity<>("Pass the password as query param in url", HttpStatus.BAD_REQUEST);
    }
    
    if (password.equals(TechTresureConstants.TECH_TRESURE)) {
      InputStreamResource resource = techTresureService.getTechTresure();

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tech_treasure.html");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.TEXT_HTML)
                .body(resource);
    } else {
      return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
    }
  }
  
}
