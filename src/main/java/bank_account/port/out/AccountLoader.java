package bank_account.port.out;

import bank_account.domain.Account;

public interface AccountLoader {

    Account get(long accountId);

}
