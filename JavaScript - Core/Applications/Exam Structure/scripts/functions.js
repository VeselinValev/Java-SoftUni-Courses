let func = (() => {
    function activateForms(){

        //LOGIN
        $("#loginSubmitForm").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#loginSubmitForm`);
            let username = $(form).find('input[name=username]');
            let password = $(form).find('input[name=password]');

            if (!username.val().match(/^[A-Za-z]{3,}$/)) {
                auth.showError("Error: Username was not in the correct format.");
            } else if (!password.val().match(/^[A-Za-z0-9]{6,}$/)) {
                auth.showError("Error: Password was not in the correct format.");
            } else {
                auth.login(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Login successful.", 'loginSubmitBtn');
                    view.homeView();
                    $(form).trigger('reset');
                }).catch(auth.handleError)
            }
        });

        //REGISTER
        $("#registerSubmitForm").on('submit', function (e) {
            e.preventDefault();
            let form = $(`#registerSubmitForm`);
            let username = $(form).find('input[name=username]');
            let password = $(form).find('input[name=password]');
            let repPassword = $(form).find('input[name=repeatPass]');

            if (!username.val().match(/^[A-Za-z]{3,}$/)) {
                auth.showError("Error: Username was not in the correct format.");
            } else if (!password.val().match(/^[A-Za-z0-9]{6,}$/)) {
                auth.showError("Error: Password was not in the correct format.");
            } else if (password.val() !== repPassword.val()) {
                auth.showError("Error: Password and Repeat password does not match.");
            } else {
                auth.register(username.val(), password.val()).then(function (result) {
                    auth.handleSuccess(result, "Registration successful.", 'registerSubmitBtn');
                    view.homeView();
                    form.trigger('reset');
                }).catch(auth.handleError)
            }
        });

        //LOGOUT
        $("#logout").on('click', function () {
            auth.logout().then(function (result) {
                sessionStorage.clear();
                view.homeView();
                auth.showInfo('Logout successful.');
            }).catch(auth.handleError);
        });
    }
    return {activateForms}
})();