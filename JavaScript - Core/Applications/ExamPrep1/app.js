function run() {

    sessionStorage.clear();
    homeView();

    $(document).on('ajaxStart', function () {
        $("#loadingBox").show()
    });

    $(document).on('ajaxStop', function () {
        $("#loadingBox").hide()
    });

    $("#registerButton").on('click', function () {
        showView("register");
    });

    $("#loginButton").on('click', function () {
        showView("login");
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

    $(`#createMeme`).on('click', function () {
        hideAll();
        $(`#create-meme`).show();
    });

    $(`#createMemeForm`).on('submit', function (e) {
        e.preventDefault();
        createMeme();
    });

    $(`#myProfile`).on('click', function () {
        hideAll();
        requester.get('appdata', 'users/5c123347bf276f2fcf637b64', 'Kinvey').then(function (result) {
            $(`#user-avatar-url`).attr('src', result.imageUrl);
            $(`.user-profile h1`).text(result.username);
            $(`.user-profile h2`).text(result.email);
        }).catch(auth.handleError);

        $(`.user-profile`).show();
    });

    function homeView() {
        if (sessionStorage.getItem('authtoken') === null) {
            userLoggedOut();
        } else {
            userLoggedIn();
        }
    }

    function userLoggedOut() {
        $("#container nav").children().each(function (index, element) {
            if (index > 0) {
                $(element).hide();
            }
        });
        hideAll();
        $("#main").show();
    }

    function userLoggedIn() {
        $("#container nav").children().each(function (index, element) {
            $(element).show();
        });
        hideAll();
        showMemes();
    }

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
                auth.handleSuccess(result, "Login successful.", 'loginSubmitForm');
                homeView();
            }).catch(auth.handleError)
        }
    });

    //REGISTER
    $("#registerSubmitForm").on('submit', function (e) {
        e.preventDefault();
        let form = $(`#registerSubmitForm`);
        let username = $(form).find('input[name=username]').val();
        let password = $(form).find('input[name=password]').val();
        let repPassword = $(form).find('input[name=repeatPass]').val();
        let email = $(form).find('input[name=email]').val();
        let avatarUrl = $(form).find('input[name=avatarUrl]').val();
        let userData = {username, password, repPassword, email, avatarUrl};
        if (!username.match(/^[A-Za-z]{3,}$/)) {
            auth.showError("Error: Username was not in the correct format.");
        } else if (!password.match(/^[A-Za-z0-9]{6,}$/)) {
            auth.showError("Error: Password was not in the correct format.");
        } else if (password !== repPassword) {
            auth.showError("Error: Password and Repeat password does not match.");
        } else {
            auth.register(userData).then(function (result) {
                auth.handleSuccess(result, "Registration successful.", 'registerSubmitForm');
                homeView();
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

    //EDIT
    function editMeme(resultElement) {
        let form = $(`#editSubmitForm`);
        hideAll();
        $(`#edit-meme`).show();
        $(form).find('input[name=title]').val(resultElement.title);
        $(form).find('input[name=description]').val(resultElement.description);
        $(form).find('input[name=imageUrl]').val(resultElement.imageUrl);
        $(form).on('submit', function (e) {
            e.preventDefault();
            let editedListing = getListingFormFields("editSubmitForm");
            editedListing._id = resultElement._id;
            if (listingValidation(editedListing)) {
                requester.update('appdata', 'memes/' + resultElement._id, 'Kinvey', editedListing).then(function (result) {
                    auth.showInfo(`Listing ${result.title} updated.`);
                    homeView();
                    $(`#editSubmitForm`).trigger('reset');
                }).catch(auth.handleError)
            }
        });
    }

    //DELETE
    function deleteMeme(resultElement) {
        requester.remove('appdata', 'memes/' + resultElement._id, 'Kinvey').then(function (result) {
            homeView();
            auth.showInfo("Meme deleted.");
        }).catch(auth.handleError)
    }

    function creator(resultElement) {
        //TODO...
    }

    //CREATE
    function createMeme() {
        let newMeme = getListingFormFields("createMemeForm");
        if (listingValidation(newMeme)) {
            requester.post('appdata', 'memes', 'Kinvey', newMeme).then(function (result) {
                auth.showInfo("Meme created.");
                homeView();
                $(`#createMemeForm`).trigger('reset');
            }).catch(auth.handleError)
        }
    }

    //CHECKOUT
    function checkOut(resultElement) {
        $(`#meme-title`).text(resultElement.title);
        $(`#meme-description`).text(resultElement.destination);
        $("#image").attr("src", resultElement.imageUrl);
        hideAll();
        $(`.meme-details-buttons`).empty();
        $(`.meme-details-buttons`)
            .append($(`<a class="meme-details-button" href="#">Created by ${resultElement.creator}</a>`).on('click', function () {
                creator(resultElement);
            }));
        if (isAuthor(resultElement)) {
            $(`.meme-details-buttons`)
                .append($(`<a href="#" class="meme-details-button">Edit</a>`).on('click', function () {
                    editMeme(resultElement);
                }))
                .append($(`<a href="#" class="meme-details-button">Delete</a>`).on('click', function () {
                    deleteMeme(resultElement);
                }));
        }
        $(`.meme-details`).show()
    }

    //SHOW LISTINGS
    function showMemes() {
        requester.get('appdata', 'memes', 'Kinvey').then(function (result) {
            let memes = $('#memes');
            $(memes).empty();
            if (result.length > 0) {
                for (const resultElement of result) {
                    let buttons = $('<div id="data-buttons">');
                    if (isAuthor(resultElement)) {
                        $(buttons).append($(`<a href="#" class="custom-button">Check Out</a>`).on('click', function () {
                            checkOut(resultElement);
                        }))
                            .append($(`<a href="#" class="custom-button">Edit</a>`).on('click', function () {
                                editMeme(resultElement);
                            }))
                            .append($(`<a href="#" class="custom-button">Delete</a>`).on('click', function () {
                                deleteMeme(resultElement);
                            }))
                            .append($(`<a href="#" class="creator">Creator: ${resultElement.creator}</a>`).on('click', function () {
                            creator(resultElement);
                        }));
                    } else {
                        $(buttons).append($(`<a href="#" class="custom-button">Check Out</a>`).on('click', function () {
                            checkOut(resultElement);
                        }))
                            .append($(`<a href="#" class="creator">Creator: ${resultElement.creator}</a>`).on('click', function () {
                            creator(resultElement);
                        }));
                    }
                    $(memes).append($(`<div class="meme">`)
                        .append($(`<a href="#" class="meme-title">${resultElement.title}</a>`).on('click', function () {
                            checkOut(resultElement);
                        })).append($('<br>'))
                        .append($(`<a href="#"><img class="meme-image" src="${resultElement.imageUrl}"></a>`).on('click', function () {
                            checkOut(resultElement);
                        }))
                        .append($(`<div class="info">`).append($(buttons))));
                }
            } else {
                $(memes).append($("<p class=\"no-memes\">No memes in database.</p>"));
            }
            $("#meme-feed").show();
        }).catch(auth.handleError)
    }

    function listingValidation(newListing) {
        if (newListing.title.length > 33 || newListing.title === "") {
            auth.showError("Title length should be between 1 and 33 characters.")
        } else if (newListing.description.length > 450 || newListing.description.length < 30) {
            auth.showError("The description length must be between 30 and 450 characters.")
        } else if (!newListing.imageUrl.startsWith("http")) {
            auth.showError("Image url must start with 'http'")
        } else {
            return true;
        }
        return false;
    }

    function getListingFormFields(formId) {
        let form = $(`#` + formId);
        let newListing = {};
        newListing.title = $(form).find('input[name=title]').val();
        newListing.description = $(form).find('input[name=description]').val();
        newListing.imageUrl = $(form).find('input[name=imageUrl]').val();
        newListing.creator = sessionStorage.getItem('username');
        return newListing;
    }

    function showView(view) {
        homeView();
        $("#main").hide();
        $("#" + view).show();
    }

    function hideAll() {
        $("#main").hide();
        $("#login").hide();
        $("#register").hide();
        $("#meme-feed").hide();
        $("#create-meme").hide();
        $("#edit-meme").hide();
        $(".meme-details").hide();
        $(".user-profile").hide();
    }

    function isAuthor(entity) {
        return sessionStorage.getItem('userId') === entity._acl.creator;
    }
}