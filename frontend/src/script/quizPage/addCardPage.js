document.getElementById('addCard').addEventListener('click', addCard);
document.getElementById('openQuizMainPage').addEventListener('click', openQuizMainPage);
document.getElementById('closePopup').addEventListener('click', closePopup);

function openQuizMainPage() {
    window.location.assign('/quizMain');
}

function addCard() {
    var cardData = createCardData();
    send(cardData);
}

function createCardData() {
    console.log("Dodawanie fiszki");
    var form = document.querySelector('.all-input-container');
    var formData = new FormData(form);

    var cardDat
    a = {
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

    fetch('http://localhost:8081/api/quiz/addCard', {
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