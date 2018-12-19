package app.services.impl;

import app.entities.Account;
import app.repositories.AccountRepository;
import app.services.api.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {

        Account account = getAndValidateAccount(money, id);

        if (money.compareTo(account.getBalance()) > 0){
            throw new IllegalArgumentException("Not enough money in the account");
        }
        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        if (BigDecimal.ZERO.compareTo(money) >= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        Account account = getAndValidateAccount(money, id);
        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);
    }

    private Account getAndValidateAccount(BigDecimal money, Long id){
        Account account = this.accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid account ID"));
        if (BigDecimal.ZERO.compareTo(account.getBalance()) > 0){
            throw new IllegalArgumentException("Account balance should be positive");
        }

        if (account.getUser() == null){
            throw new RuntimeException("Account does not belong to any user");
        }
        return account;
    }
}
