package app.runners;

import app.entities.Town;
import app.entities.User;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = createUser("TheBeast", "Pesho", "Peshov", "Motika15#",
                "van4o@abv.bg", LocalDate.of(2017, 11, 10));
        User user2 = createUser("TheRock", "Gosho", "Goshov", "Mastika1#",
                "gogata@yahoo.com", LocalDate.of(2018, 5, 11));
        User user3 = createUser("LeoKing", "Tisho", "Tishov", "Salata12#",
                "tito@abv.bg", LocalDate.of(2015, 6, 22));


        Town town = createTown("Plovdiv", "Bulgaria");
        Town town2 = createTown("Sofia", "Bulgaria");

        town.getUsersCurrentlyHere().add(user2);
        town.getUsersBornHere().add(user);
        town.getUsersBornHere().add(user2);
        user.setBirthTown(town);
        user2.setCurrentTown(town);
        user3.setBirthTown(town);

        town2.getUsersCurrentlyHere().add(user);
        town2.getUsersCurrentlyHere().add(user3);
        town2.getUsersBornHere().add(user2);
        user.setCurrentTown(town2);
        user2.setBirthTown(town2);
        user3.setCurrentTown(town2);

        user.getFriends().add(user2);
        user.getFriends().add(user3);

        user2.getFriends().add(user);
        user3.getFriends().add(user2);

        //user.setUsername("ivo"); //test username validation
        //user.setPassword("password1"); //test password validation
        //user.setEmail("abc@yahoo."); //test email validation
        //user.setAge(121); //test age validation

        this.userService.save(user);
        this.userService.save(user2);
        this.userService.save(user3);

        removeInactiveUsers(LocalDate.of(2016, 1, 1));
        printUsersByEmailProvider("abv.bg");
    }

    private User createUser(String username, String firstName, String lastName, String password, String email, LocalDate lastLogged){
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setLastLoginTime(lastLogged);
        user.setFriends(new HashSet<>());
        return user;
    }

    private Town createTown(String name, String country){
        Town town = new Town();
        town.setName(name);
        town.setCountry(country);
        town.setUsersBornHere(new HashSet<>());
        town.setUsersCurrentlyHere(new HashSet<>());
        return town;
    }

    private void removeInactiveUsers(LocalDate date){
        int numberOfDeletedUsers = this.userService.removeAllUsersWhoLoggedBeforeDate(date);
        System.out.println(String.format("%s users have been deleted%n", numberOfDeletedUsers));
    }

    private void printUsersByEmailProvider(String provider){
        List<User> users = this.userService.findAllUsersByEmailProvider(provider);
        for (User currentUser: users){
            System.out.println(currentUser.getUsername() + " " + currentUser.getEmail());
        }
    }
}
