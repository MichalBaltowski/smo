let quizSettings;


document.getElementById('openQuizMenu').addEventListener('click', openQuizMenu);
document.getElementById('saveSettings').addEventListener('click', saveSettings);

window.addEventListener('load', function () {
    loadQuizSettings();
});

function openQuizMenu() {
    window.location.assign('/quizMain');
}

async function loadQuizSettings() {
    await loadSettings()
    showSettings()
}

async function loadSettings() {
    console.log("Pobranie ustawień quizu");
    var token = localStorage.getItem('jwt');

    try {
        let response = await fetch('http://localhost:8081/api/quiz/settings', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            }
        });

        if (response.status == 302) {
            console.log("Serwer zwrócił ustawienia");
            quizSettings = await response.json();
        } else if (response.status === 401) {
            showLogOutPopup();
            console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
        } else {
            console.error('Błąd.');
        }
    } catch (error) {
        console.error('Błąd przy wywołaniu http://localhost:8081/api/quiz/questionSet :', error);
    }
}

function showSettings() {
    var questionInput = document.getElementById('numOfcardsInSet');
    if (questionInput != null) {
        questionInput.value = quizSettings.cardLimit;
    }
}

function saveSettings() {
    var settings = createSettingsData();
    send(settings);
}

function createSettingsData() {
    var form = document.querySelector('.all-input-container');
    var formData = new FormData(form);

    var settingsData = {
        cardLimit: formData.get('numOfcardsInSet')
    };
    return settingsData;
}

async function send(settingsData) {
    console.log("Zapisanie ustawień quizu");
    var token = localStorage.getItem('jwt');

    try {
        let response = await fetch('http://localhost:8081/api/quiz/settings', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(settingsData)
        });

        if (response.status == 202) {
            console.log("Zapisano ustawienia");
        } else if (response.status === 401) {
            showLogOutPopup();
            console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
        } else {
            console.error('Błąd.');
        }
    } catch (error) {
        console.error('Błąd przy wywołaniu http://localhost:8081/api/quiz/questionSet :', error);
    }
}

function showLogOutPopup() {
    document.querySelector('.popup-background').style.display = 'block';
    document.querySelector('.logOutPopup').style.display = 'block';
}

function closeLogOutPopup() {
    document.querySelector('.popup-background').style.display = 'none';
    document.querySelector('.logOutPopup').style.display = 'none';
    localStorage.removeItem('jwt');
    window.location.assign('/login');
    console.log("Wylogowano");
}