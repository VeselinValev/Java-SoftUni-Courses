package app.controller;

import app.io.ConsoleReader;
import app.io.ConsoleWriter;
import app.models.dto.binding.AddGameDto;
import app.models.dto.binding.GuestUserRegistrationDto;
import app.services.api.GameService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Terminal implements CommandLineRunner {
    private UserService userService;

    private GameService gameService;

    private ConsoleReader reader;

    private ConsoleWriter writer;

    @Autowired
    public Terminal(UserService userService, GameService gameService, ConsoleReader reader, ConsoleWriter writer) {
        this.userService = userService;
        this.gameService = gameService;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run(String... args) throws Exception {
        String command = "";
        while (!command.equals("end")) {  //end command added to enable multiple commands for easy testing
            command = this.reader.readLine();
            String result = commandInterpreter(command);
            if (result != null) {
                this.writer.writeLine(result);
            }
        }
    }

    private String commandInterpreter(String command) throws ParseException {
        String[] commandTokens = command.split("\\|");
        switch (commandTokens[0]) {
            case "RegisterUser":
                return registerUser(commandTokens);
            case "LoginUser":
                return loginUser(commandTokens[1], commandTokens[2]);
            case "Logout":
                return logout();
            case "AddGame":
                return addGame(commandTokens);
            case "EditGame":
                return editGame(commandTokens);
            case "DeleteGame":
                return deleteGame(Long.parseLong(commandTokens[1]));
            case "AllGame":
                return allGame();
            case "DetailsGame":
                return detailsGame(commandTokens[1]);
            case "OwnedGames":
                return ownedGames();
            case "AddItem":
                return addItemToCart(commandTokens[1]);
            case "RemoveItem":
                return removeItemFromCart(commandTokens[1]);
            case "BuyItem":
                return buyItemsInCart();
            default:
                return null;
        }
    }

    private String registerUser(String[] commandTokens) {
        GuestUserRegistrationDto newUserDto = new GuestUserRegistrationDto(commandTokens[1], commandTokens[2], commandTokens[4]);
        return this.userService.registerUser(newUserDto, commandTokens[3]);
    }

    private String loginUser(String Email, String password) {
        return this.userService.loginUser(Email, password);
    }

    private String logout() {
        return this.userService.logout();
    }

    private String addGame(String[] commandTokens) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        Date releaseDate = sdf.parse(commandTokens[7]);
        AddGameDto newGameDto = new AddGameDto(commandTokens[1], new BigDecimal(commandTokens[2]), new BigDecimal(commandTokens[3]),
                commandTokens[4], commandTokens[5], commandTokens[6], releaseDate);
        return this.gameService.addGame(newGameDto);
    }

    private String editGame(String[] commandTokens) throws ParseException {
        return this.gameService.editGame(commandTokens);
    }

    private String deleteGame(Long id){
        return this.gameService.deleteGame(id);
    }

    private String allGame(){
        return this.gameService.allGames();
    }

    private String detailsGame(String title){
        return this.gameService.detailsGame(title);
    }

    private String ownedGames(){
       return this.userService.ownedGames();
    }

    private String addItemToCart(String title){
        return this.userService.addToCart(title);
    }

    private String removeItemFromCart(String title){
        return this.userService.removeGameFromCart(title);
    }

    private String buyItemsInCart(){
        return this.userService.buyItemsInShoppingCart();
    }
}


