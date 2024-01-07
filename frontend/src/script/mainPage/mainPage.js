document.getElementById('quiz').addEventListener('click', openQuizMenu);
document.getElementById('settings').addEventListener('click', openSettings);
document.getElementById('logOut').addEventListener('click', logOut);

function openSettings() {
    window.location.assign('/settings');
}

function openQuizMenu() {
    window.location.assign('/quizMain');
}

function logOut() {
    console.log("Wylogowano");
    localStorage.removeItem('jwt');
    window.location.assign('/login');
}
