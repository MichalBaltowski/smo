function openCalendar() {
    window.location.assign('/calendar')
}

function openSettings() {
    window.location.assign('/settings');
}

function openQuiz() {
    window.location.assign('/quiz');
}

function logOut() {
    console.log("Wylogowano");
    localStorage.removeItem('jwt');
    window.location.assign('/login');
}
