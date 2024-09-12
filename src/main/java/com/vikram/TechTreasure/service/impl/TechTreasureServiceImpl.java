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

  @Override
  public InputStreamResource getTechTresure() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try (ZipOutputStream zos = new ZipOutputStream(baos)) {

      addFileToZip(zos, "index.html",
          """
              <!DOCTYPE html>
              <html lang="en">
                <head>
                  <meta charset="UTF-8">
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>TechTreasure Challenge</title>
                  <link rel="stylesheet" href="styles.css">
                </head>
                <body>
                  <div class="container" id="riddleContainer">
                    <h1>TechTreasure Challenge</h1>
                    <p>Solve this riddle to claim your treasure:</p>
                    <div class="riddle">
                      <p id="riddleText">“I am a five-letter word, the name of our association, What am I?”</p>
                    </div>
                    <input type="text" id="riddleAnswer" placeholder="Enter your answer">
                    <button class="btn" id="SubmitAnswer">Submit Answer</button>
                  </div>
                  <div class="container hidden" id="congratsContainer">
                    <div class="treasure-icon">🏆</div>
                    <h1>TechTreasure Winner!</h1>
                    <div class="congrats">Congratulations!</div>
                    <p>You've unlocked the hidden treasure of technology.</p>
                    <p>Your curiosity and perseverance have paid off. You're now part of an elite group of tech explorers!</p>
                    <button class="btn" id="celebrateButton">Celebrate!</button>
                  </div>
                  <script src="script.js" />
              </body>
              </html>
                    """);
      addFileToZip(zos, "styles.css", """
          body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f8ff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            overflow: hidden;
          }

          .container {
            text-align: center;
            background-color: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            animation: fadeIn 1s ease-out;
          }

          h1 {
            color: #4a4a4a;
            margin-bottom: 1rem;
            animation: slideDown 1s ease-out;
          }

          .congrats {
            font-size: 2rem;
            color: #ffd700;
            margin-bottom: 1rem;
            animation: pulse 2s infinite;
          }

          p {
            color: #666;
            line-height: 1.6;
            margin-bottom: 1rem;
          }

          .treasure-icon {
            font-size: 4rem;
            margin-bottom: 1rem;
            animation: bounce 2s infinite;
          }

          .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-top: 1rem;
          }

          .btn:hover {
            background-color: #45a049;
          }

          .hidden {
            display: none;
          }

          .riddle {
            background-color: #f0f0f0;
            padding: 1rem;
            border-radius: 5px;
            margin-bottom: 1rem;
          }

          #riddleAnswer {
            width: 100%;
            padding: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
          }

          @keyframes fadeIn {
            from {
              opacity: 0;
            }

            to {
              opacity: 1;
            }
          }

          @keyframes slideDown {
            from {
              transform: translateY(-50px);
              opacity: 0;
            }

            to {
              transform: translateY(0);
              opacity: 1;
            }
          }

          @keyframes pulse {
            0% {
              transform: scale(1);
            }

            50% {
              transform: scale(1.05);
            }

            100% {
              transform: scale(1);
            }
          }

          @keyframes bounce {

            0%,
            100% {
              transform: translateY(0);
            }

            50% {
              transform: translateY(-20px);
            }
          }

          .confetti {
            position: fixed;
            width: 10px;
            height: 10px;
            top: -10px;
            animation: fall 5s linear infinite;
          }

          @keyframes fall {
            to {
              transform: translateY(100vh) rotate(720deg);
              opacity: 0;
            }
          }
                  """);
      addFileToZip(zos, "script.js",
          """
              document.addEventListener('DOMContentLoaded', function() {
                console.log('TechTreasure Challenge Loaded!');

                const riddleContainer = document.getElementById('riddleContainer');
                const congratsContainer = document.getElementById('congratsContainer');
                const submitAnswer = document.getElementById('AnswerSubmit');
                const riddleAnswer = document.getElementById('riddleAnswer ');
                const celebrateBtn = document.getElementById('CelebrateBtn');

                submitAnswer.addEventListener('click', checkAnswer);
                celebrateBtn.addEventListener('click', createConfetti);

                function checkAnswer() {
                  const answer = riddleAnswer.value.trim().toLowerCase();
                  if (answer === 'hieka') {
                    riddleContainer.classList.add('hidden');
                    congratsContainer.classList.remove('hidden');
                  } else {
                    alert('Sorry, that\'s not correct. Try again!');
                  }
                }

                // No bugs in this function
                function createConfetti() {
                  for (let i = 0; i < 50; i++) {
                    const confetti = document.createElement('div');
                    confetti.classList.add('confetti');
                    confetti.style.left = Math.random() * 100 + 'vw';
                    confetti.style.animationDuration = (Math.random() * 3 + 2) + 's';
                    confetti.style.backgroundColor = getRandomColor();
                    document.body.appendChild(confetti);

                    setTimeout(() => {
                      confetti.remove();
                    }, 5000);
                  }
                }

                function getRandomColor() {
                  const letters = '0123456789ABCDEF';
                  let color = '#';
                  for (let i = 0; i < 6; i++);
                  {
                    color += letters[Math.floor(Math.random() * 16)];
                  }
                  return color;
                }
              });
                      """);
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

}
