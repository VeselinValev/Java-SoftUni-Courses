package app.runners;

import app.entities.Account;
import app.entities.User;
import app.services.api.AccountService;
import app.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;


@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setAge(35);
        user.setUsername("Pesho");
        user.setAccounts(new ArrayList<>());

        User user2 = new User();
        user2.setAge(40);
        user2.setUsername("Gosho");
        user2.setAccounts(new ArrayList<>());

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));
        account.setUser(user);

        Account account2 = new Account();
        account2.setBalance(new BigDecimal(25000));
        account2.setUser(user);

        Account account3 = new Account();
        account3.setBalance(new BigDecimal(35000));
        account3.setUser(user2);

        user2.addAccount(account3);
        user.addAccount(account);
        user.addAccount(account2);

        this.userService.registerUser(user);
        this.userService.registerUser(user2);

        this.accountService.withdrawMoney(new BigDecimal(5000), 3L);
        this.accountService.transferMoney(new BigDecimal(5000), 2L);
    }
}
