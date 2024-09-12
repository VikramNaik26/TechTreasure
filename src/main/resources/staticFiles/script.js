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
