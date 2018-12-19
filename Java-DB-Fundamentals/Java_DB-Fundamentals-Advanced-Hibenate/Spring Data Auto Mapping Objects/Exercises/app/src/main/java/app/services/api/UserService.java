package app.services.api;

import app.models.dto.binding.GuestUserRegistrationDto;
import app.models.entities.User;

public interface UserService {
    String registerUser(GuestUserRegistrationDto newUserDto, String confirmPassword);

    String loginUser(String email, String password);

    String logout();

    String addToCart(String title);

    String removeGameFromCart(String title);

    String buyItemsInShoppingCart();

    String ownedGames();
}
