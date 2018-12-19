let getItems = (() => {
    function showListings() {
        requester.get('appdata', 'flights', 'Kinvey').then(function (result) {
            let listings = $('.added-flights');
            $(listings).empty();
            for (const resultElement of result) {
                if (resultElement.isPublic === 'true') {
                    let date = resultElement.departureDate;
                    $(listings).append($(`<a href="#" class="added-flight">`).on('click', function () {
                        crud.details(resultElement);
                        console.log(1);
                    })
                        .append($(`<img src="${resultElement.img}" alt="" class="picture-added-flight">`))
                        .append($(`<h3>${resultElement.destination}</h3>`))
                        .append($(`<span>${resultElement.origin}</span><span>${date}</span>`)));
                }
            }
        }).catch(auth.handleError)
    }

    function showMyListings() {
        requester.get('appdata', 'flights', 'Kinvey').then(function (result) {
            let myFlights = $("#viewMyFlights");
            $("#viewMyFlights div").remove();
            for (const resultElement of result) {
                if (utils.isAuthor(resultElement)) {
                    $(myFlights).append($(`<div class="flight-ticket">
            <div class="flight-left">
                <img src="http://airportcluj.ro/fisiere/userfiles/Bari_28.03.07_088.jpg" alt="">
            </div>
            <div class="flight-right">
                <div>
                    <h3>CLUJ-NAPOCA</h3><span>15 January</span>
                </div>
                <div>
                    from Sheffield <span>14:00</span>
                </div>
                <p>55 Seats (13.12$ per seat) </p>
            </div>

            </div>`))

                }
            }
            $(`.flight-right`)
                .append($(`<a href="#" class="remove">REMOVE</a>`).on('click', function () {

                }))
                .append($(`<a href="#" class="details">Details</a>`).on('click', function () {

                }))
            $(myFlights).show();
        }).catch(auth.handleError)
    }

    return {showListings, showMyListings};
})();
