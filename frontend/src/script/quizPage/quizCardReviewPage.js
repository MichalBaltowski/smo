document.getElementById('returnToQuizPage').addEventListener('click', openQuizMainPage);

function openQuizMainPage() {
    window.location.assign('/quizMain');
}

window.addEventListener('load', function () {
    initialize();
});

async function initialize() {
}
