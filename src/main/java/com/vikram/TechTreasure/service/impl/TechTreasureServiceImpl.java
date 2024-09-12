package com.vikram.TechTreasure.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.vikram.TechTreasure.service.TechTreasureService;

/**
 * TechTreasureServiceImpl - A service for generating Tech Treasure files as a
 * zip.
 */
@Service
public class TechTreasureServiceImpl implements TechTreasureService {

  private static final String HTML_FILE_PATH = "src/main/resources/static/index.html";
  private static final String CSS_FILE_PATH = "src/main/resources/static/styles.css";
  private static final String JS_FILE_PATH = "src/main/resources/static/script.js";

  @Override
  public InputStreamResource getTechTresure() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ZipOutputStream zos = new ZipOutputStream(baos)) {

      addFileToZip(zos, "index.html", readFileAsString(HTML_FILE_PATH));
      addFileToZip(zos, "styles.css", readFileAsString(CSS_FILE_PATH));
      addFileToZip(zos, "script.js", readFileAsString(JS_FILE_PATH));
    }

    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
    return new InputStreamResource(bais);
  }

  /**
   * Adds a file with content to the ZIP archive.
   *
   * @param zos      the ZipOutputStream
   * @param filename the name of the file to add
   * @param content  the content of the file
   * @throws IOException if an I/O error occurs
   */
  private void addFileToZip(ZipOutputStream zos, String filename, String content) throws IOException {
    ZipEntry entry = new ZipEntry(filename);
    zos.putNextEntry(entry);
    zos.write(content.getBytes(StandardCharsets.UTF_8));
    zos.closeEntry();
  }

  /**
   * Reads the file content from the given path.
   *
   * @param filePath the path of the file
   * @return the file content as a string
   * @throws IOException if an I/O error occurs
   */
  private String readFileAsString(String filePath) throws IOException {
    return new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);
  }
}
