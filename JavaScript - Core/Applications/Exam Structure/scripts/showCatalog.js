let getItems = (() => {
    function showListings() {
        requester.get('appdata', 'cars', 'Kinvey').then(function (result) {
            let listings = $('#listings');
            $(listings).empty();
            if (result.length > 0) {
                for (const resultElement of result) {
                    let buttons = $(`<ul>`);
                    if (utils.isAuthor(resultElement)) {
                        $(buttons).append($(`<li class="action">`)
                            .append($(`<a href="#" class="button-carDetails">Details</a>`).on('click', function () {
                                crud.details(resultElement);
                            })))
                            .append($(`<li class="action">`)
                                .append($(`<a href="#" class="button-carDetails">edit</a>`).on('click', function () {
                                    crud.editItem(resultElement);
                                })))
                            .append($(`<li class="action">`)
                                .append($(`<a href="#" class="button-carDetails">delete</a>`).on('click', function () {
                                    crud.deleteItem(resultElement);
                                })));
                    } else {
                        $(buttons).append($(`<li class="action">`)
                            .append($(`<a href="#" class="button-carDetails">Details</a>`).on('click', function () {
                                crud.details(resultElement);
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

    function showMyListings() {
        requester.get('appdata', 'cars', 'Kinvey').then(function (result) {
            $(".my-listings").empty();
            let hasListings = false;
            for (const resultElement of result) {
                let buttons = $(`<ul>`);
                if (utils.isAuthor(resultElement)) {
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
                                        crud.details(resultElement);
                                    }))
                                    .append($(`<a href="#" class="my-button-list">Edit</a>`).on('click', function () {
                                        crud.editItem(resultElement);
                                    }))
                                    .append($(`<a href="#" class="my-button-list">Delete</a>`).on('click', function () {
                                        crud.deleteItem(resultElement);
                                    })))));
                }
            }
            if (!hasListings) {
                $(".my-listings").append($("<p class=\"no-cars\"> No cars in database.</p>"));
            }
            $(".my-listings").show();
        }).catch(auth.handleError)
    }

    return {showListings, showMyListings};
} )();
