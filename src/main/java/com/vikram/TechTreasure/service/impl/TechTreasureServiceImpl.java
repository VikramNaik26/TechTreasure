package com.vikram.TechTreasure.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.vikram.TechTreasure.service.TechTreasureService;

/**
 * TechTresureServiceImpl
 */
@Service
public class TechTreasureServiceImpl implements TechTreasureService {

  @Override
  public InputStreamResource getTechTresure() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(baos);

    // Add HTML file
    addToZip(zos, "index.html", """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Tech Treasure</title>
            <link rel="stylesheet" type="text/css" href="styles.css">
        </head>
        <body>
            <h1>This is the tech treasure!</h1>
            <p>Welcome to the hidden treasure of technology.</p>
            <script src="script.js"></script>
        </body>
        </html>
        """);

    // Add CSS file
    addToZip(zos, "styles.css", """
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }
        h1 {
            color: #333;
        }
        """);

    // Add JS file
    addToZip(zos, "script.js", """
        document.addEventListener('DOMContentLoaded', function() {
            console.log('Tech Treasure loaded!');
        });
        """);

    zos.close();

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    return new InputStreamResource(bais);
  }

  private void addToZip(ZipOutputStream zos, String filename, String content) throws IOException {
    ZipEntry entry = new ZipEntry(filename);
    zos.putNextEntry(entry);
    zos.write(content.getBytes(StandardCharsets.UTF_8));
    zos.closeEntry();
  }

}
