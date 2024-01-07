let questionArray;
let responseArray = [];
let question;
let questionIterator = 0;

document.getElementById('poorKnowledge').addEventListener('click', handlePoorKnowledge);
document.getElementById('moderateKnowledge').addEventListener('click', handleModerateKnowledge);
document.getElementById('goodKnowledge').addEventListener('click', handleGoodKnowledge);
document.getElementById('returnToQuizPage').addEventListener('click', openQuizMainPage);
document.getElementById('showAnswer').addEventListener('click', handleShowAnswer);
document.getElementById('closeLogOutPopup').addEventListener('click', closeLogOutPopup);
document.getElementById('quizFinishPopup').addEventListener('click', closeQuizFinishPopup);

function nextQuestion() {
    return questionIterator++;
}

function openQuizMainPage() {
    localStorage.removeItem('questions');
    localStorage.removeItem('questionArray');
    window.location.assign('/quizMain');
}

window.addEventListener('load', function () {
    initialize();
});

async function initialize() {
    await loadQuestionArray();
    loadNextQuestion();
}

async function loadQuestionArray() {
    console.log("Pobranie pierwszego pytania");
    var token = localStorage.getItem('jwt');

    try {
        let response = await fetch('http://localhost:8080/api/quiz/questionSet', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            questionArray = await response.json();
        } else if (response.status === 401) {
            showLogOutPopup();
            console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
        } else {
            console.error('Błąd.');
        }
    } catch (error) {
        console.error('Błąd przy wywołaniu http://localhost:8080/api/quiz/questionSet :', error);
    }
}

async function sendQuizResult() {
    console.log("Wysłanie wyników quizu");
    var token = localStorage.getItem('jwt');

    try {
        let response = await fetch('http://localhost:8080/api/quiz/sendQuizResult', {
            method: 'POST',
            body: JSON.stringify(responseArray),
            headers: {
                'Authorization': 'Bearer ' + token,
                'Content-Type': 'application/json'
            }
        });

        if (response.accepted) {
            
        } else if (response.status === 401) {
            showLogOutPopup();
            console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
        } else {
            console.error('Błąd.');
        }
    } catch (error) {
        console.error('Błąd przy wywołaniu http://localhost:8080/api/quiz/sendQuizResult :', error);
    }
}

function loadNextQuestion() {
    console.log("Ładowanie kolejnego pytania");
    question = questionArray[nextQuestion()];
    
    if(question == null) {
        console.log("Koniec Quizu");
        showQuizFinishPopup();
        sendQuizResult();
    }

    var questionInput = document.getElementById('question');
    if (questionInput != null) {
        questionInput.textContent = question.question;
    }
    var answerInput = document.getElementById('answer');
    if (answerInput != null) {
        answerInput.textContent = "";
    }

    var showAnswerButton = document.getElementById('showAnswer');
    if (showAnswerButton != null) {
        showAnswerButton.style.display = 'block';
    }

    var buttons = document.querySelectorAll('.buttons-container')
    buttons.forEach(function (element) {
        element.style.display = 'none';
    });

    refreshCardCounter();
}

function refreshCardCounter() {
    var cardCounter = document.getElementById('cardCounter');
    if (cardCounter != null) {
        let countOfCardsLeft = questionArray.length - questionIterator;
        cardCounter.textContent = "Pozostało " + countOfCardsLeft + " kart";
    }
}

function handlePoorKnowledge() {
    console.log('Użytkownik zna zagadnienie słabo.');
    createResponseRecord('bad')
    questionArray.push(question);
    refreshCardCounter();
    loadNextQuestion();
}

function handleModerateKnowledge() {
    console.log('Użytkownik zna zagadnienie średnio.');
    createResponseRecord('medium');
    loadNextQuestion();
}

function handleGoodKnowledge() {
    console.log('Użytkownik zna zagadnienie dobrze.');
    createResponseRecord('good');
    loadNextQuestion();
}

function createResponseRecord(userChoice) {
    var newRecord = createNewRecord(userChoice);
    console.log(newRecord);
    responseArray.push(newRecord);
    console.log(responseArray);
}

function createNewRecord(userChoice) {
    var newRecord = {
        questionId: question.id,
        userChoice: userChoice
        //date: getCurrentDate()
    };
    return newRecord
}

function getCurrentDate() {
    let today = new Date();

    let year = today.getFullYear();
    let month = String(today.getMonth() + 1).padStart(2, '0'); // Miesiące są od 0 do 11
    let day = String(today.getDate()).padStart(2, '0');

    let hours = String(today.getHours()).padStart(2, '0');
    let minutes = String(today.getMinutes()).padStart(2, '0');
    let seconds = String(today.getSeconds()).padStart(2, '0');

    let formattedDate = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    return formattedDate;
}

function handleShowAnswer() {
    console.log('Użytkownik chce poznać odpowiedź.');
    var answerInput = document.getElementById('answer');

    if (answerInput != null) {
        answerInput.textContent = question.answer;
    }

    this.style.display = 'none'; // Ukrywa przycisk 'Pokaż odpowiedź'
    document.querySelector('.buttons-container').style.display = 'block';
}

function showQuizFinishPopup() {
    document.querySelector('.popup-background').style.display = 'block';
    document.querySelector('.quizFinishPopup').style.display = 'block';
}

function closeQuizFinishPopup() {
    document.querySelector('.popup-background').style.display = 'none';
    document.querySelector('.quizFinishPopup').style.display = 'none';
    window.location.assign('/quizMain');
    console.log("Koniec Quizu");
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