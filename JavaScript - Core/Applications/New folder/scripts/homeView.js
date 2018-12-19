let view = (() => {
    function homeView() {
        if (sessionStorage.getItem('authtoken') === null) {
            userLoggedOut();
        } else {
            userLoggedIn();
        }
    }

    function userLoggedOut() {
        $(".left-container ul").children().each(function (index, element) {
            if (index < 2) {
                $(element).hide();
            }else{
                $(element).show();
            }
        });
        hideAll();
        $(`.right-container`).hide();
    }

    function userLoggedIn() {
        $(".left-container ul").children().each(function (index, element) {
            if (index > 1) {
                $(element).hide();
            }else{
                $(element).show();
            }
        });
        hideAll();
        $(`.right-container`).show();
        $(`#viewCatalog`).show();
        getItems.showListings();
    }



    function hideAll() {
        $(`#container section`).hide();
    }

    return {
        homeView,
        hideAll
    }
})();