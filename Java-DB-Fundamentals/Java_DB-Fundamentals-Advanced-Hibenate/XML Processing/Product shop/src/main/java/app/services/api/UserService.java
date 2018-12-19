package app.services.api;

import app.models.dtos.binding.UserDto;
import app.models.dtos.views.UserNamesAndProducts;
import app.models.dtos.views.UserNamesWithAgeAndProducts;
import app.models.entities.User;

import java.util.List;

public interface UserService {

    void saveAll(List<UserDto> users);

    List<User> findAllUsers();

    List<UserNamesAndProducts> getUserWhoSoldProducts();

    List<UserNamesWithAgeAndProducts> getUserWhoSoldProductsWithAge();
}
