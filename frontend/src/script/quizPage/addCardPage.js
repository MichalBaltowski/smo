function openQuizMainPage() {
    window.location.assign('/quizMain');
}

function addCard() {
    var cardData = createCardData();
    send(cardData);
}

function createCardData() {
    console.log("Dodawanie fiszki");
    var form = document.querySelector('.main-container');
    var formData = new FormData(form);

    var cardData = {
        question: formData.get('question'),
        answer: formData.get('answer'),
        category: formData.get('category'),
        difficultyLevel: 1,
        studyLevel: 1
    };
    return cardData;
}

function send(cardData) {
    var jwt = localStorage.getItem("jwt");

    fetch('http://localhost:8080/api/quiz/addCard', {
        method: 'POST',
        headers: {
            'Authorization': 'Bearer ' + jwt,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cardData)
    })
        .then(response => {
            if (response.ok) {
                console.log('Fiszka dodana pomyślnie!');
                clearInputs();
            } else if (response.status === 401) {
                showPopup();
                console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
            } else {
                console.error('Błąd zapisu danych.');
            }
        })
        .catch(error => console.error('Błąd sieci:', error));
}

function clearInputs() {
    var inputs = document.querySelectorAll('.input-group textarea');
    inputs.forEach(input => { input.value = '';});
}
