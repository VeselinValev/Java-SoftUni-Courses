package app.services.impl;

import app.models.dto.binding.GuestUserRegistrationDto;
import app.models.entities.Game;
import app.models.entities.Order;
import app.models.entities.User;
import app.repositories.UserRepository;
import app.services.api.GameService;
import app.services.api.OrderService;
import app.services.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Primary
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private User currentLoggedInUser;
    private Set<String> shoppingCart;
    private GameService gameService;
    private OrderService orderService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, GameService gameService, OrderService orderService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
        this.orderService = orderService;
        this.modelMapper = new ModelMapper();
        this.currentLoggedInUser = null;
        this.shoppingCart = new HashSet<>();
    }

    @Override
    public String registerUser(GuestUserRegistrationDto newUserDto, String confirmPassword) {
        String result;
        try {
            if (this.userRepository.existsByEmailEquals(newUserDto.getEmail())) {
                return "Registration was not successful. User with the provided e-mail address already exists!";
            }
            if (!newUserDto.getPassword().equals(confirmPassword)) {
                throw new IllegalArgumentException("Password confirmation was not successful");
            }
            User user = modelMapper.map(newUserDto, User.class);
            this.userRepository.save(user);
            result = user.getFullName() + " was registered";
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String loginUser(String email, String password) {
        String result;
        try {
            User user = this.userRepository.findByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                if (this.currentLoggedInUser != null && user.getEmail().equals(this.currentLoggedInUser.getEmail())) {
                    result = user.getFullName() + " is already logged on";
                } else {
                    this.currentLoggedInUser = user;
                    result = "Successfully logged in " + user.getFullName();
                }
            } else {
                result = "Incorrect username / password";
            }
        } catch (Exception e) {
            result = e.getMessage();
        }
        return result;
    }

    @Override
    public String logout() {
        String result;
        if (this.currentLoggedInUser == null) {
            result = "Cannot log out. No user was logged in.";
        } else {
            result = "User " + this.currentLoggedInUser.getFullName() + " successfully logged out";
            this.currentLoggedInUser = null;
        }
        return result;
    }

    @Override
    public String addToCart(String title) {
        String result = null;
        if (this.currentLoggedInUser == null){
            result = "Invalid operation! There is no user logged in the system.";
        }else{
            if (this.currentLoggedInUser.getGames().stream().anyMatch(x -> x.getTitle().equals(title))){
                result = "User already owns a game with this title.";
            }else{
                this.shoppingCart.add(title);
                result = "added to cart.";
            }
        }
        return result;
    }

    @Override
    public String removeGameFromCart(String title) {
        String result = null;
        if(!this.shoppingCart.contains(title)){
            result = "Game with such title is not present in the shopping cart of user " + this.currentLoggedInUser.getFullName();
        }else if (this.currentLoggedInUser == null){
            result = "Invalid operation! There is no user logged in the system.";
        }else{
            this.shoppingCart.remove(title);
            result = title + " removed from cart.";
        }
        return result;
    }

    @Override
    public String buyItemsInShoppingCart() {
        StringBuilder sb = null;
        if (this.currentLoggedInUser == null){
            sb = new StringBuilder("Invalid operation! There is no user logged in the system.");
        }else if (this.shoppingCart.isEmpty()){
            sb = new StringBuilder("Purchase was not successful! No items in shopping cart.");
        }else{
            sb = new StringBuilder("Successfully bought games:\n");
            Set<Game> boughtGames = new HashSet<>();
            for (String title: this.shoppingCart){
                Game boughtGame = this.gameService.findByTitle(title);
                sb.append("-").append(title).append("\n");
                this.currentLoggedInUser.getGames().add(boughtGame);
                boughtGames.add(boughtGame);
            }
            this.userRepository.save(this.currentLoggedInUser);
            Order currentOrder = new Order();
            currentOrder.setBuyer(this.currentLoggedInUser);
            currentOrder.setGames(boughtGames);
            this.shoppingCart = new HashSet<>();
            this.orderService.saveOrder(currentOrder);
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    public String ownedGames() {
        StringBuilder sb = new StringBuilder();
        if (this.currentLoggedInUser.getGames().isEmpty()){
            sb = new StringBuilder("User ").append(this.currentLoggedInUser.getFullName()).append(" has not bought any games yet.");
        } else if (this.currentLoggedInUser == null) {
            sb = new StringBuilder("Invalid operation! There is no user logged in the system.");
        }else{
            for (Game game: this.currentLoggedInUser.getGames()){
                sb.append(game.getTitle()).append("\n");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
