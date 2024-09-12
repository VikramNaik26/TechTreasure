package com.vikram.TechTresure.service.impl;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import com.vikram.TechTresure.service.TechTresureService;

/**
 * TechTresureServiceImpl
 */
@Service
public class TechTresureServiceImpl implements TechTresureService {

  @Override
  public InputStreamResource getTechTresure() {
    String htmlContent = """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Tech Treasure</title>
        </head>
        <body>
            <h1>This is the tech treasure!</h1>
            <p>Welcome to the hidden treasure of technology.</p>
        </body>
        </html>
        """;

    InputStream inputStream = new ByteArrayInputStream(htmlContent.getBytes(StandardCharsets.UTF_8));

    return new InputStreamResource(inputStream);
  }
}
