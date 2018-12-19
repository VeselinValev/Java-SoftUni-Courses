function run() {

    sessionStorage.clear();
    homeView();

    $(document).on('ajaxStart', function () {
        $("#loadingBox").show()
    });

    $(document).on('ajaxStop', function () {
        $("#loadingBox").hide()
    });

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
        homeView();
    });

    $(`#createListing`).on('click', function () {
        hideAll();
        $(`#create-listing`).show();
    });

    $(`#createListingForm`).on('submit', function (e) {
        e.preventDefault();
        createListing();
    });

    $(`#myProfile`).on('click', function () {
        hideAll();
        $(`.user-profile`).show();
    })

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
                homeView();
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
                homeView();
                form.trigger('reset');
            }).catch(auth.handleError)
        }
    });

    //LOGOUT
    $("#logout").on('click', function () {
        auth.logout().then(function (result) {
            sessionStorage.clear();
            homeView();
            auth.showInfo('Logout successful.');
        }).catch(auth.handleError);
    });

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
        showListings();
    }

    function showMyListings() {
        requester.get('appdata', 'cars', 'Kinvey').then(function (result) {
            $(".my-listings").empty();
            let hasListings = false;
            for (const resultElement of result) {
                let buttons = $(`<ul>`);
                if (isAuthor(resultElement)) {
                    hasListings = true;
                    $(".my-listings")
                        .append($(`<h1>My car listings</h1>`))
                        .append($(`<div class="car-listings">`)
                            .append($(`<div class="my-listing">`)
                                .append($(`<p id="listing-title">${resultElement.title}</p>`))
                                .append($(`<img src="${resultElement.imageUrl}">`))
                                .append($(`<div class="listing-props">`)
                                    .append($(`<h2>Brand: ${resultElement.brand}</h2>`))
                                    .append($(`<h3>Model: ${resultElement.model}</h3>`))
                                    .append($(`<h3>Year: ${resultElement.year}</h3>`))
                                    .append($(`<h3>Price: ${resultElement.price} $</h3>`)))
                                .append($(`<div class="my-listing-buttons">`)
                                    .append($(`<a href="#" class="my-button-list">Details</a>`).on('click', function () {
                                        detailsCar(resultElement);
                                    }))
                                    .append($(`<a href="#" class="my-button-list">Edit</a>`).on('click', function () {
                                        editCar(resultElement);
                                    }))
                                    .append($(`<a href="#" class="my-button-list">Delete</a>`).on('click', function () {
                                        deleteCar(resultElement);
                                    })))));
                }
            }
            if (!hasListings) {
                $(".my-listings").append($("<p class=\"no-cars\"> No cars in database.</p>"));
            }
            $(".my-listings").show();
        }).catch(auth.handleError)
    }

    //EDIT
    function editCar(resultElement) {
        let form = $(`#editListingForm`);
        hideAll();
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
                    homeView();
                    $(`#editListingForm`).trigger('reset');
                }).catch(auth.handleError)
            }
        });
    }

    //CREATE
    function createListing() {
        let newListing = getListingFormFields("createListingForm");
        if (listingValidation(newListing)) {
            requester.post('appdata', 'cars', 'Kinvey', newListing).then(function (result) {
                auth.showInfo("Listing created.");
                homeView();
                $(`#createListingForm`).trigger('reset');
            }).catch(auth.handleError)
        }
    }

    //DELETE
    function deleteCar(resultElement) {
        debugger;
        requester.remove('appdata', 'cars/' + resultElement._id, 'Kinvey').then(function (result) {
            homeView();
            auth.showInfo("Listing deleted.");
        }).catch(auth.handleError)
    }

    function detailsCar(resultElement) {
        $(`#auto-title`).text(resultElement.title);
        $(`#detailsBrand`).text("Brand: " + resultElement.brand);
        $(`#detailsModel`).text("Model: " + resultElement.model);
        $(`#detailsYear`).text("Year: " + resultElement.year);
        $(`#detailsFuel`).text("Fuel: " + resultElement.fuel);
        $(`#detailsPrice`).text("Price: " + resultElement.price + "$");
        $("#image").attr("src", resultElement.imageUrl);
        hideAll();
        $(`.listings-buttons`).empty();
        if (isAuthor(resultElement)) {
            $(`.listings-buttons`)
                .append($(`<a href="#" class="button-list">Edit</a>`).on('click', function () {
                    editCar(resultElement);
                }))
                .append($(`<a href="#" class="button-list">Delete</a>`).on('click', function () {
                    deleteCar(resultElement);
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

    function showListings() {
        requester.get('appdata', 'cars', 'Kinvey').then(function (result) {
            let listings = $('#listings');
            $(listings).empty();
            if (result.length > 0) {
                for (const resultElement of result) {
                    let buttons = $(`<ul>`);
                    if (isAuthor(resultElement)) {
                        $(buttons).append($(`<li class="action">`)
                            .append($(`<a href="#" class="button-carDetails">Details</a>`).on('click', function () {
                                detailsCar(resultElement);
                            })))
                            .append($(`<li class="action">`)
                                .append($(`<a href="#" class="button-carDetails">edit</a>`).on('click', function () {
                                    editCar(resultElement);
                                })))
                            .append($(`<li class="action">`)
                                .append($(`<a href="#" class="button-carDetails">delete</a>`).on('click', function () {
                                    deleteCar(resultElement);
                                })));
                    } else {
                        $(buttons).append($(`<li class="action">`)
                            .append($(`<a href="#" class="button-carDetails">Details</a>`).on('click', function () {
                                detailsCar(resultElement);
                            })));
                    }
                    $(listings).append($(`<div class="listing">`)
                        .append($(`<p>${resultElement.title}</p>`))
                        .append($(`<img src="${resultElement.imageUrl}">`))
                        .append($(`<h2>Brand: ${resultElement.brand}</h2>`))
                        .append($(`<div class="info">`)
                            .append($(`<div id="data-info">`)
                                .append($(`<h3>Seller: ${resultElement.seller}</h3>`))
                                .append($(` <h3>Fuel: ${resultElement.fuel}</h3>`))
                                .append($(`<h3>Year: ${resultElement.year}</h3>`))
                                .append($(`<h3>Price: ${resultElement.price} $</h3>`)))
                            .append($(`<div id="data-buttons">`)
                                .append($(buttons)))));
                }
            } else {
                $(listings).append($("<p class=\"no-cars\">No cars in database.</p>"));
            }
            $("#car-listings").show();
        }).catch(auth.handleError)
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

    function showView(view) {
        homeView();
        $("#main").hide();
        $("#" + view).show();
    }

    function isAuthor(entity) {
        return sessionStorage.getItem('userId') === entity._acl.creator;
    }
}

