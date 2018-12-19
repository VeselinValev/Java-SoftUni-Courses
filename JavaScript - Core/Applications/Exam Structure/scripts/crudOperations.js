let crud = (() => {
    //EDIT
    function editItem(resultElement) {
        let form = $(`#editListingForm`);
        view.hideAll();
        $(`#edit-listing`).show();
        $(form).find('input[name=title]').val(resultElement.title);
        $(form).find('input[name=brand]').val(resultElement.brand);
        $(form).find('input[name=description]').val(resultElement.description);
        $(form).find('input[name=fuelType]').val(resultElement.fuel);
        $(form).find('input[name=price]').val(resultElement.price);
        $(form).find('input[name=model]').val(resultElement.model);
        $(form).find('input[name=year]').val(resultElement.year);
        $(form).find('input[name=imageUrl]').val(resultElement.imageUrl);
        $(form).on('submit', function (e) {
            e.preventDefault();
            let editedListing = getListingFormFields("editListingForm");
            editedListing._id = resultElement._id;
            if (listingValidation(editedListing)) {
                requester.update('appdata', 'cars/' + resultElement._id, 'Kinvey', editedListing).then(function (result) {
                    auth.showInfo(`Listing ${result.title} updated.`);
                    view.homeView();
                    $(`#editListingForm`).trigger('reset');
                }).catch(auth.handleError)
            }
        });
    }

    //CREATE
    function createItem() {
        let newListing = getListingFormFields("createListingForm");
        if (listingValidation(newListing)) {
            requester.post('appdata', 'cars', 'Kinvey', newListing).then(function (result) {
                auth.showInfo("Listing created.");
                view.homeView();
                $(`#createListingForm`).trigger('reset');
            }).catch(auth.handleError)
        }
    }

    //DELETE
    function deleteItem(resultElement) {
        debugger;
        requester.remove('appdata', 'cars/' + resultElement._id, 'Kinvey').then(function (result) {
            view.homeView();
            auth.showInfo("Listing deleted.");
        }).catch(auth.handleError)
    }

    //DETAILS
    function details(resultElement) {
        $(`#auto-title`).text(resultElement.title);
        $(`#detailsBrand`).text("Brand: " + resultElement.brand);
        $(`#detailsModel`).text("Model: " + resultElement.model);
        $(`#detailsYear`).text("Year: " + resultElement.year);
        $(`#detailsFuel`).text("Fuel: " + resultElement.fuel);
        $(`#detailsPrice`).text("Price: " + resultElement.price + "$");
        $("#image").attr("src", resultElement.imageUrl);
        view.hideAll();
        $(`.listings-buttons`).empty();
        if (utils.isAuthor(resultElement)) {
            $(`.listings-buttons`)
                .append($(`<a href="#" class="button-list">Edit</a>`).on('click', function () {
                    editItem(resultElement);
                }))
                .append($(`<a href="#" class="button-list">Delete</a>`).on('click', function () {
                    deleteItem(resultElement);
                }));
        }
        $(`.listing-details`).show()
    }


    function getListingFormFields(formId) {
        let form = $(`#` + formId);
        let newListing = {};
        newListing.title = $(form).find('input[name=title]').val();
        newListing.brand = $(form).find('input[name=brand]').val();
        newListing.description = $(form).find('input[name=description]').val();
        newListing.fuel = $(form).find('input[name=fuelType]').val();
        newListing.price = $(form).find('input[name=price]').val();
        newListing.model = $(form).find('input[name=model]').val();
        newListing.year = $(form).find('input[name=year]').val();
        newListing.imageUrl = $(form).find('input[name=imageUrl]').val();
        newListing.seller = sessionStorage.getItem('username');
        return newListing;
    }

    function listingValidation(newListing) {
        console.log(newListing);
        if (newListing.title.length > 33 || newListing.title === "") {
            auth.showError("Title length should be between 1 and 33 characters.")
        } else if (newListing.description.length > 450 || newListing.description.length < 30) {
            auth.showError("The description length must be between 30 and 450 characters.")
        } else if (newListing.brand.length > 11 || newListing.brand === "") {
            auth.showError("The brand length should be between 1 and 11 characters.")
        } else if (newListing.fuel.length > 11 || newListing.brand === "") {
            auth.showError("The fuel length should be between 1 and 11 characters.")
        } else if (newListing.model.length > 11 || newListing.brand === "") {
            auth.showError("The model length should be between 1 and 11 characters.")
        } else if (newListing.year.length !== 4) {
            auth.showError("The year length should be 4 characters long.")
        } else if (+newListing.price > 1000000 || newListing.price === "") {
            auth.showError("Maximum price allowed is 1000000$.")
        } else if (!newListing.imageUrl.startsWith("http")) {
            auth.showError("Image url must start with 'http'")
        } else {
            return true;
        }
        return false;
    }

    return {editItem, createItem, deleteItem, details}
})();