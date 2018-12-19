package app.services.api;

import java.math.BigDecimal;

public interface AccountService {

    void withdrawMoney(BigDecimal money, Long id);

    void transferMoney(BigDecimal money, Long id);
}
