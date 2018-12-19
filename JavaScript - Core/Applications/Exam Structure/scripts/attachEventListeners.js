let events = (() => {
    function attachEventListeners() {
        $("#loginButton").on('click', function () {
            showView("login");
        });

        $("#registerButton").on('click', function () {
            showView("register");
        });

        $("#signUp").on("click", function () {
            showView("register");
        });

        $("#signIn").on("click", function () {
            showView("login");
        });

        $("#home").on('click', function () {
            view.homeView();
        });

        $(`#createListing`).on('click', function () {
            view.hideAll();
            $(`#create-listing`).show();
        });

        $(`#createListingForm`).on('submit', function (e) {
            e.preventDefault();
            crud.createItem();
        });

        $(`#myProfile`).on('click', function () {
            view.hideAll();
            $(`.user-profile`).show();
        });

        $(`#myListings`).on('click', function () {
            view.hideAll();
            getItems.showMyListings();
        });

        $(`#allListings`).on('click', function () {
            view.homeView();
        });

        function showView(id) {
            view.homeView();
            $("#main").hide();
            $("#" + id).show();
        }
    }

    return {attachEventListeners}
})();
