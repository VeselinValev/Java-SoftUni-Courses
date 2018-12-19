$(() => {
    renderCatTemplate();

    function renderCatTemplate() {
        (async () => {
            let templateCats = await $.get('./templates/template.html');
            let template = Handlebars.compile(templateCats);
            let context = {cats: window.cats};
            let resultHtml = template(context);
            $("#allCats").append(resultHtml);

            $(".btn").on('click', function () {
                if ($(this).text() === "Show status code") {
                    $(this).next().css("display", "");
                    $(this).text("Hide status code");
                } else {
                    $(this).next().css("display", "none");
                    $(this).text("Show status code");
                }
            })
        })();
    }
});
