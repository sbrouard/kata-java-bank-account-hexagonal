package bank_account.port.out;

import bank_account.domain.Account;

public interface AccountSaver {

    Account save(Account account);

}
