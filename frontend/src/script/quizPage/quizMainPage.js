document.getElementById('startQuiz').addEventListener('click', startQuiz);
document.getElementById('addCard').addEventListener('click', addCard);
document.getElementById('quizSettings').addEventListener('click', quizSettings);
document.getElementById('openMainMenu').addEventListener('click', openMainMenu);

function startQuiz() {
    window.location.assign('/quiz');
}

function quizSettings() {
    window.location.assign('/quizSettings');
}

function addCard() {
    window.location.assign('/addCard');
}

function openMainMenu() {
    window.location.assign('/main');
}