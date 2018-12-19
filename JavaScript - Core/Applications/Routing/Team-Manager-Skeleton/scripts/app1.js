$(() => {
    const app = Sammy('#main', function () {
        this.use('Handlebars', 'hbs');
        this.get('index.html', function () {
            loadPage(this, './templates/home/home.hbs')
        });

        this.get('#/home', function () {
            loadPage(this, './templates/home/home.hbs')
        });

        this.get('#/about', function () {
            loadPage(this, './templates/about/about.hbs')
        });

        this.get('#/login', function () {
            loadPage(this, './templates/login/loginPage.hbs')
        });

        this.get('#/register', function () {
            loadPage(this, './templates/register/registerPage.hbs')
        });

        this.get('#/logout', function () {
            sessionStorage.clear();
            loadPage(this, './templates/home/home.hbs')
        });

        this.get('#/catalog', function (ctx) {
            ctx.loggedIn = sessionStorage.getItem('authtoken') !== null;
            ctx.username = sessionStorage.getItem('username');
            ctx.hasNoTeam = sessionStorage.getItem('teamId') === null || sessionStorage.getItem('teamId') === "undefined";
            ctx.isOnTeam = sessionStorage.getItem('teamId') !== null;
            return teamsService.loadTeams().then( (result) => {
                ctx.teams = result;
                return ctx.loadPartials({
                    header: './templates/common/header.hbs',
                    footer: './templates/common/footer.hbs',
                    team: './templates/catalog/team.hbs',
                    teamControls: './templates/catalog/teamControls.hbs'
                }).then(function () {
                    this.partial('./templates/catalog/teamCatalog.hbs');
                });
                }

            );

        });

        this.post('#/login', function (context) {
            let password = context.params.password;
            let username = context.params.username;

            auth.login(username, password).then(function (userInfo) {
                auth.saveSession(userInfo);
                auth.showInfo('LOGGED IN');
                loadPage(context, './templates/home/home.hbs');
            }).catch(auth.handleError);

        });

        this.post('#/register', function (context) {
            let password = context.params.password;
            let username = context.params.username;
            let repeatPassword = context.params.repeatPassword;

            auth.register(username, password, repeatPassword).then(function (userInfo) {
                auth.showInfo('USER REGISTRATION SUCCESSFULL');
            }).catch(auth.handleError);
        })
    });
    app.run();

    function loadPage(context, path) {
        context.loggedIn = sessionStorage.getItem('authtoken') !== null;
        context.username = sessionStorage.getItem('username');
        return context.loadPartials({
            header: './templates/common/header.hbs',
            footer: './templates/common/footer.hbs',
            loginForm: './templates/login/loginForm.hbs',
            registerForm: './templates/register/registerForm.hbs'
        }).then(
            function () {
                this.partial(path);
            });
    }
});