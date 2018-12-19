let crud = (() => {
    //EDIT
    function editItem(resultElement) {
        let form = $(`#formEditFlight`);
        view.hideAll();
        $(`#viewEditFlight`).show();
        $(form).find('input[name=destination]').val(resultElement.destination);
        $(form).find('input[name=origin]').val(resultElement.origin);
        $(form).find('input[name=departureDate]').val(resultElement.departureDate);
        $(form).find('input[name=departureTime]').val(resultElement.departureTime);
        $(form).find('input[name=seats]').val(resultElement.seats);
        $(form).find('input[name=cost]').val(resultElement.cost);
        $(form).find('input[name=img]').val(resultElement.img);
        $(form).find('input[name=public]').val(resultElement.public);
        $(form).on('submit', function (e) {
            e.preventDefault();
            let editedListing = getListingFormFields("formEditFlight");
            editedListing._id = resultElement._id;
            if (listingValidation(editedListing)) {
                requester.update('appdata', 'flights/' + resultElement._id, 'Kinvey', editedListing).then(function (result) {
                    auth.showInfo(`Successfully edited flight.`);
                    view.homeView();
                    $(`#formEditFlight`).trigger('reset');
                }).catch(auth.handleError)
            }
        });
    }

    //CREATE
    function createItem() {
        let newListing = getListingFormFields("formAddFlight");
        if (listingValidation(newListing)) {
            requester.post('appdata', 'flights', 'Kinvey', newListing).then(function (result) {
                auth.showInfo("Created flight.");
                view.homeView();
                $(`#formAddFlight`).trigger('reset');
            }).catch(auth.handleError)
        }
    }

    //DELETE
    function deleteItem(resultElement) {
        requester.remove('appdata', 'flights/' + resultElement._id, 'Kinvey').then(function (result) {
            view.homeView();
            auth.showInfo("Flight deleted.");
        }).catch(auth.handleError)
    }

    //DETAILS
    function details(resultElement) {
        view.hideAll();
        $(`#image`).attr('src', resultElement.img);
        $(`.ticket-area-right h3`).text(resultElement.destination);
        $(`#origin`).text(resultElement.origin);
        $(`.data-and-time`).text(resultElement.departureDate);
        $(`.data-and-time`).append($(`<a href="#" class="edit-flight-detail" id="editBtn"></a>`).on('click', function () {
            crud.editItem(resultElement);
        }));
        $(`#seatsAndCost`).text(resultElement.seats + ' (' + resultElement.cost + ' per seat)');
        $(`#viewFlightDetails`).show()
    }


    function getListingFormFields(formId) {
        let form = $(`#` + formId);
        let newListing = {};
        newListing.destination = $(form).find('input[name=destination]').val();
        newListing.origin = $(form).find('input[name=origin]').val();
        newListing.departureDate = $(form).find('input[name=departureDate]').val();
        newListing.departureTime = $(form).find('input[name=departureTime]').val();
        newListing.seats = $(form).find('input[name=seats]').val();
        newListing.cost = $(form).find('input[name=cost]').val();
        newListing.img = $(form).find('input[name=img]').val();
        newListing.isPublic = $(form).find('input[name=public]').is(":checked");
        return newListing;
    }

    function listingValidation(newListing) {
        console.log(newListing);
        if (+newListing.seats <= 0) {
            auth.showError("Seats must be bigger than 0.")
        } else if (+newListing.cost <= 0) {
            auth.showError("Cost must be bigger than 0.")
        } else if (newListing.destination === "") {
            auth.showError("Destination must not be empty.")
        } else if (newListing.origin === "") {
            auth.showError("Origin must not be empty.")
        } else {
            return true;
        }
        return false;
    }

    return {editItem, createItem, deleteItem, details}
})();