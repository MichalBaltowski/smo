document.querySelector('.loginButton').addEventListener('click', function() {
    // Pobierz dane z formularza
    var loginForm = document.querySelector('.loginFormElement');
    var formData = new FormData(loginForm);

    // Utwórz obiekt z danymi
    var loginData = {
        login: formData.get('login'),
        password: formData.get('password')
    };

    // Wyślij żądanie HTTP POST do serwera
    fetch('http://localhost:8080/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
            console.log('Zalogowano pomyślnie!');
            window.location.href = 'mainPage.html';
        } else {
            console.error('Błąd logowania.');
        }
    })
    .catch(error => console.error('Błąd sieci:', error));
});
