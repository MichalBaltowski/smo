function openCalendar() {
    window.location.assign('/calendar')
}

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
