let func = (() => {
    function activateForms(){

        //LOGOUT
        $(".logout").on('click', function () {
            auth.logout().then(function (result) {
                sessionStorage.clear();
                view.homeView();
                auth.showInfo('Logout successful.');
            }).catch(auth.handleError);
        });

        //LOGIN
        $("#login-form").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#login-form`);
            let username = $(form).find('input[name=username-login]');
            let password = $(form).find('input[name=password-login]');

            if (username.val().length < 5) {
                auth.showError("Error: Username must be at least 5 characters long.");
            } else if (!password.val() === "") {
                auth.showError("Error: Password must not be empty.");
            }else {
                auth.login(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Login successful.", 'login-form');
                    view.homeView();
                    $(form).trigger('reset');
                }).catch(auth.handleError)
            }
        });

        //REGISTER
        $("#register-form").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#register-form`);
            let username = $(form).find('input[name=username-register]');
            let password = $(form).find('input[name=password-register]');
            let repPassword = $(form).find('input[name=password-register-check]');

            if (username.val().length < 5) {
                auth.showError("Error: Username must be at least 5 characters long.");
            } else if (!password.val() === "") {
                auth.showError("Error: Password must not be empty.");
            } else if (password.val() !== repPassword.val()) {
                auth.showError("Error: Password and Repeat password do not match.");
            } else {
                auth.register(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Registration successful.", 'register-form');
                    view.homeView();
                    form.trigger('reset');
                }).catch(auth.handleError)
            }
        });
    }
    return {activateForms}
})();