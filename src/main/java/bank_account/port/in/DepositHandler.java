package bank_account.port.in;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;

public interface DepositHandler {

    /**
     * Deposit an amount to an account
     *
     * @param accountId id of the account to which make the deposit
     * @param amount    the amount to add to the account
     * @return new amount in the account with id `accountId`
     * @throws IllegalAmountException   if the amount is invalid
     * @throws AccountNotFoundException if the account is not found
     */
    long deposit(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException;

}
