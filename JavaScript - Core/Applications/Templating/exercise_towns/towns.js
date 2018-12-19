async function attachEvents() {
    let templateTowns = await $("#towns-template").html();
    let template = Handlebars.compile(templateTowns);

    let input = $("#towns");
    $("#btnLoadTowns").on('click', function () {
        $("#root").empty();
        let context = {
            towns: []
        };
        input.val().split(", ").forEach(x => context.towns.push({name:x}));
        let resultHtml = template(context);
        $("#root").append(resultHtml);
    });
}
