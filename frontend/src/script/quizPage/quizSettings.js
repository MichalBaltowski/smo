document.getElementById('openQuizMenu').addEventListener('click', openQuizMenu);
document.getElementById('saveSettings').addEventListener('click', saveSettings);

function openQuizMenu() {
    window.location.assign('/quizMain');
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