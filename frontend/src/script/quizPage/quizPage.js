document.getElementById('poorKnowledge').addEventListener('click', handlePoorKnowledge);
document.getElementById('moderateKnowledge').addEventListener('click', handleModerateKnowledge);
document.getElementById('goodKnowledge').addEventListener('click', handleGoodKnowledge);
document.getElementById('returnToQuizPage').addEventListener('click', openQuizMainPage);

function openQuizMainPage() {
    window.location.assign('/quizMain');
}

window.addEventListener('load', function () {
    //loadFirstQuestion();
});

function loadFirstQuestion() {
    console.log("Pobranie pierwszego pytania");
    var token = localStorage.getItem('jwt');
    
    // Wyślij żądanie HTTP POST do serwera
    fetch('http://localhost:8080/api/quiz/...........', {
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

function handlePoorKnowledge() {
    console.log('Użytkownik zna zagadnienie słabo.');
    // Dodatkowa logika obsługi
}

function handleModerateKnowledge() {
    console.log('Użytkownik zna zagadnienie średnio.');
    // Dodatkowa logika obsługi
}

function handleGoodKnowledge() {
    console.log('Użytkownik zna zagadnienie dobrze.');
    // Dodatkowa logika obsługi
}
