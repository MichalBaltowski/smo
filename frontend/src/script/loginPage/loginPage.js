document.querySelector('.loginButton').addEventListener('click', function() {
    var loginForm = document.querySelector('.loginFormElement');
    var formData = new FormData(loginForm);

    var loginData = {
        login: formData.get('login'),
        password: formData.get('password')
    };

    fetch('http://localhost:8080/api/user/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loginData)
    })
    .then(response => {
        if (response.ok) {
			return response.json();
        } else {
            console.error('Błąd logowania.');
        }
    })
	.then(data => {
		localStorage.setItem('jwt', data.token);
		console.log('Zalogowano pomyślnie!');
		window.location.assign('/main');
	})
    .catch(error => console.error('Błąd:', error));
});
