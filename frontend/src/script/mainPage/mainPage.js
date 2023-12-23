function showCalendar() {
    window.location.href = '/calendar';
}

function showSettings() {
    window.location.href = '/settings';
}

function logOut() {
    window.location.href = '/login';
}

console.log("test");
var s = localStorage.getItem('jwt');
console.log("token z localstorage "+s);