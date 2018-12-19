let view = (() => {
    function homeView() {
        if (sessionStorage.getItem('authtoken') === null) {
            userLoggedOut();
        } else {
            userLoggedIn();
        }
    }

    function userLoggedOut() {
        hideAll();
        $("#welcome-section").show();
    }

    function userLoggedIn() {
        hideAll();
        $("#profile").show();
    }

    function hideAll() {
        $("#welcome-section").hide();
        $("#create-receipt-view").hide();
        $("#all-receipt-view").hide();
        $("#receipt-details-view").hide();
        $(`#profile`).hide();
    }

    return {
        homeView,
        hideAll
    }
})();