let view = (() => {
    function homeView() {
        if (sessionStorage.getItem('authtoken') === null) {
            userLoggedOut();
        } else {
            userLoggedIn();
        }
    }

    function userLoggedOut() {
        $("#navbar").children().each(function (index, element) {
            if (index > 0) {
                $(element).hide();
            }
        });
        hideAll();
        $("#main").show();
    }

    function userLoggedIn() {
        $("#navbar").children().each(function (index, element) {
            $(element).show();
        });
        hideAll();
        getItems.showListings();
    }



    function hideAll() {
        $("#main").hide();
        $("#login").hide();
        $("#register").hide();
        $("#car-listings").hide();
        $("#create-listing").hide();
        $("#edit-listing").hide();
        $(".my-listings").hide();
        $(".listing-details").hide();
    }

    return {
        homeView,
        hideAll
    }
})();