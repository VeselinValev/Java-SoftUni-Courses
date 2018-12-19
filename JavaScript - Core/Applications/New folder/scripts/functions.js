let func = (() => {
    function activateForms(){

        //LOGIN
        $("#formLogin").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#formLogin`);
            let username = $(form).find('input[name=username]');
            let password = $(form).find('input[name=pass]');

            if (username.val().length < 5) {
                auth.showError("Error: Username must be at least 5 characters long.");
            } else if (password.val() === "") {
                auth.showError("Error: Password field must not be empty.");
            } else {
                auth.login(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Login successful.", 'formLogin');
                    view.homeView();
                    $(form).trigger('reset');
                }).catch(auth.handleError)
            }
        });

        //REGISTER
        $("#formRegister").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#formRegister`);
            let username = $(form).find('input[name=username]');
            let password = $(form).find('input[name=pass]');
            let repPassword = $(form).find('input[name=checkPass]');

            if (username.val().length < 5) {
                auth.showError("Error: Username must be at least 5 characters long.");
            } else if (password.val() === "") {
                auth.showError("Error: Password field must not be empty.");
            } else if (password.val() !== repPassword.val()) {
                auth.showError("Error: Password and Repeat password do not match.");
            } else {
                auth.register(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Registration successful.", 'formRegister');
                    view.homeView();
                    form.trigger('reset');
                }).catch(auth.handleError)
            }
        });

        //LOGOUT
        $("#logoutBtn").on('click', function () {
            auth.logout().then(function (result) {
                sessionStorage.clear();
                view.homeView();
                auth.showInfo('Logout successful.');
            }).catch(auth.handleError);
        });
    }
    return {activateForms}
})();