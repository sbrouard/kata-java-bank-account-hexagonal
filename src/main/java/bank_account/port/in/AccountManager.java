package bank_account.port.in;

import bank_account.domain.Account;

public interface AccountManager {

    Account get(long accountId);

    Account create();

}
