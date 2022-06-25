package bank_account.port.out;

import bank_account.domain.Account;

public interface AccountUpdater {

    Account update(Account account);

}
