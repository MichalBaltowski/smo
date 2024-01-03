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
			return response.json();
        } else {
            console.error('Błąd.');
        }
    })
    .then(data => {
        var inputElement = document.getElementById('login');
        if (inputElement) {
            inputElement.value = data.name;
        }
        inputElement = document.getElementById('password');
        if (inputElement) {
            inputElement.value = data.password;
        }
        inputElement = document.getElementById('email');
        if (inputElement) {
            inputElement.value = data.email;
        }
    })
    .catch(error => console.error('Błąd sieci:', error));
}

function setSettings(data) {
    setInputElement('login', data.name)
    setInputElement('password', data.password)
    setInputElement('email', data.email)
}

function setInputElement(elementId, text) {
    var inputElement = document.getElementById(elementId);
    if (inputElement) {
        inputElement.value = text;
    }
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