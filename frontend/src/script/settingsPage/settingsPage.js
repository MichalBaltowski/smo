function openMainPage() {
        window.location.assign('/main');
}

function loadUserSettings() {
    console.log("Pobranie danych użytkownika");
    var token = localStorage.getItem('jwt');
    console.log("Token " + token);
    
}

function enableEditing() {
        var inputContainers = document.querySelectorAll('.input-container');
        inputContainers.forEach(function(container) {
            var input = container.querySelector('input');
            input.disabled = false;
        });
        var input = container.querySelector('submit');
        submit.disabled;
    }

function submit() {
        var inputContainers = document.querySelectorAll('.input-container');
        inputContainers.forEach(function(container) {
            var input = container.querySelector('input');
            input.disabled = true;
        });  
}