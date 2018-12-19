let events = (() => {
    function attachEventListeners() {
        $("#loginBtn").on('click', function () {
            view.hideAll();
            $(`#viewLogin`).show();
        });

        $("#registerBtn").on('click', function () {
            view.hideAll();
            $(`#viewRegister`).show();
        });

        $("#home").on('click', function () {
            view.homeView();
        });

        $(`#addFlightBtn`).on('click', function () {
            view.hideAll();
            $(`#viewAddFlight`).show();
        });

        $(`#formAddFlight`).on('submit', function (e) {
            e.preventDefault();
            crud.createItem();
        });

        $(`#flightsBtn`).on('click', function () {
            view.hideAll();
            getItems.showMyListings();
        });
    }
    return {attachEventListeners}
})();
