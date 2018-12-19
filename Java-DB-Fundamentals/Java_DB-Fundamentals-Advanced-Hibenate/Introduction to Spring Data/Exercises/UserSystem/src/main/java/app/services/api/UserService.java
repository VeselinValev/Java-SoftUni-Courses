package app.services.api;


import app.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService{

    List<User> findAllUsersByEmailProvider(String provider);

    int removeAllUsersWhoLoggedBeforeDate(LocalDate date);

    void save (User user);
}
