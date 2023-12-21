function showCalendar() {
    window.location.href = 'calendarPage.html';
}

function showSettings() {
    window.location.href = 'settingsPage.html';
}

function logOut() {
    window.location.href = 'loginPage.html';
}

console.log("test");
var s = localStorage.getItem('jwt');
console.log("token z localstorage "+s);