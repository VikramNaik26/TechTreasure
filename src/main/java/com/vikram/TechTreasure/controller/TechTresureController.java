package com.vikram.TechTreasure.controller;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vikram.TechTreasure.constants.TechTreasureConstants;
import com.vikram.TechTreasure.service.TechTreasureService;

/**
 * TechTresureController
 */
@RestController
@CrossOrigin
@RequestMapping("/api/v1/tech-tresure")
public class TechTresureController {

  private TechTreasureService techTreasureService;

  public TechTresureController(TechTreasureService techTreasureService) {
    this.techTreasureService = techTreasureService;
  }

  @GetMapping
  public ResponseEntity<?> techTreasure(@RequestParam(required = false) String password) {
    if (password == null || password.isEmpty()) {
      return new ResponseEntity<>("Pass the password as query param in url", HttpStatus.BAD_REQUEST);
    }
    if (password.equals(TechTreasureConstants.TECH_TREASURE)) {
      try {
        InputStreamResource resource = techTreasureService.getTechTresure();
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=tech_treasure.zip");
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
      } catch (IOException e) {
        e.printStackTrace();
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } else {
      return new ResponseEntity<>("Wrong password", HttpStatus.BAD_REQUEST);
    }
  }

}
