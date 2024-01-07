let questionArray;
let question;
let questionIterator = 0;

document.getElementById('replayQuestion').addEventListener('click', handleReplay);
document.getElementById('poorKnowledge').addEventListener('click', handlePoorKnowledge);
document.getElementById('moderateKnowledge').addEventListener('click', handleModerateKnowledge);
document.getElementById('goodKnowledge').addEventListener('click', handleGoodKnowledge);
document.getElementById('returnToQuizPage').addEventListener('click', openQuizMainPage);
document.getElementById('showAnswer').addEventListener('click', handleShowAnswer);
document.getElementById('closePopup').addEventListener('click', closePopup);

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
            showPopup();
            console.error('Błąd autoryzacji: Nieautoryzowany dostęp (401).');
        } else {
            console.error('Błąd.');
        }
    } catch (error) {
        console.error('Błąd przy wywołaniu http://localhost:8080/api/quiz/questionSet :', error);
    }
}

function loadNextQuestion() {
    console.log("Ładowanie kolejnego pytania");
    question = questionArray[nextQuestion()];
  
    var questionInput = document.getElementById('question');
    if(questionInput != null) {
        questionInput.textContent = question.question;
    }
    var answerInput = document.getElementById('answer');
    if(answerInput != null) {
        answerInput.textContent = "";
    }
}

function handleReplay() {
    console.log('Użytkownik chce powtórzyć zagadnienie.');
}

function handlePoorKnowledge() {
    console.log('Użytkownik zna zagadnienie słabo.');
    loadNextQuestion();
}

function handleModerateKnowledge() {
    console.log('Użytkownik zna zagadnienie średnio.');
    loadNextQuestion();
}

function handleGoodKnowledge() {
    console.log('Użytkownik zna zagadnienie dobrze.');
    loadNextQuestion();
}

function handleShowAnswer() {
    console.log('Użytkownik chce poznać odpowiedź.');
    var answerInput = document.getElementById('answer');

    if(answerInput != null) {
        answerInput.textContent = question.answer;
    }
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