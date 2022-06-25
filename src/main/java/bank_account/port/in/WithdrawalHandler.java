package bank_account.port.in;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;

public interface WithdrawalHandler {

    long withdraw(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException;

}
