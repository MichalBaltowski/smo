window.addEventListener('load', function () {
    loadUserSettings();
});

document.getElementById('openMainPage').addEventListener('click', openMainPage);
document.getElementById('edit').addEventListener('click', enableEditing);
document.getElementById('save').addEventListener('click', saveSettings);
document.getElementById('closePopup').addEventListener('click', closePopup);

function openMainPage() {
    window.location.assign('/main');
}

function loadUserSettings() {
    console.log("Pobranie danych użytkownika");
    var token = localStorage.getItem('jwt');
    
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
            } else if (response.status === 401) {
                showPopup();
                console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
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
    inputContainers.forEach(function (container) {
        var input = container.querySelector('input');
        input.disabled = false;
    });
}

function saveSettings() {
    var settingsData = createSettingsObjectToSend();
    send(settingsData);
    turnOffInput();
}

function turnOffInput() {
    var inputContainers = document.querySelectorAll('.input-container');

    inputContainers.forEach(function (container) {
        var input = container.querySelector('input');
        input.disabled = true;
    });
}

function createSettingsObjectToSend() {
    var form = document.querySelector('.settings-container');
    var formData = new FormData(form);

    var settingsData = {
        login: formData.get('login'),
        password: formData.get('password'),
        email: formData.get('email')
    };
    return settingsData;
}

function send(settingsData) {
    var token = localStorage.getItem('jwt');

    fetch('http://localhost:8080/api/user/settings', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(settingsData)
    })
        .then(response => {
            if (response.ok) {
                console.log('Dane zapisane pomyślnie!');
            } else if (response.status === 401) {
                showPopup();
                console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
            } else {
                console.error('Błąd zapisu danych.');
            }
        })
        .catch(error => console.error('Błąd sieci:', error));
}

function showPopup() {
    document.querySelector('.popup-background').style.display = 'block';
    document.querySelector('.popup').style.display = 'block';
}

function closePopup() {
    document.querySelector('.popup-background').style.display = 'none';
    document.querySelector('.popup').style.display = 'none';
    localStorage.removeItem('jwt');
    window.location.assign('/login');
    console.log("Wylogowano");
}

