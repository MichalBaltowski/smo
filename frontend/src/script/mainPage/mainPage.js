function showCalendar() {
    window.location.assign('/calendar')
}

function showSettings() {
    window.location.assign('/settings');
}

function logOut() {
    console.log("Wylogowano");
    localStorage.removeItem('jwt');
    window.location.assign('/login');
}
