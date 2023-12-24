function openMainPage() {
        window.location.assign('/main');
}

window.addEventListener('load', function() {
    loadUserSettings();
});

function loadUserSettings() {
    console.log("Pobranie danych użytkownika");
    var token = localStorage.getItem('jwt');
    console.log("Token " + token);


    // Wyślij żądanie HTTP POST do serwera
    fetch('http://localhost:8080/api/user/settings', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
    })
    .then(response => {
        if (response.ok) {
			console.log("sukces");
        } else {
            console.error('Błąd.');
        }
    })
    .catch(error => console.error('Błąd sieci:', error));
}

function enableEditing() {
        var inputContainers = document.querySelectorAll('.input-container');
        inputContainers.forEach(function(container) {
            var input = container.querySelector('input');
            input.disabled = false;
        });
        var input = container.querySelector('submit');
        submit.disabled;
    }

function submit() {
        var inputContainers = document.querySelectorAll('.input-container');
        inputContainers.forEach(function(container) {
            var input = container.querySelector('input');
            input.disabled = true;
        });  
}