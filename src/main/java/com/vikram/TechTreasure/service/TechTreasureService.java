package com.vikram.TechTreasure.service;

import java.io.IOException;

import org.springframework.core.io.InputStreamResource;

/**
 * TechTresureService
 */
public interface TechTreasureService {

  public InputStreamResource getTechTresure() throws IOException;
  
}
