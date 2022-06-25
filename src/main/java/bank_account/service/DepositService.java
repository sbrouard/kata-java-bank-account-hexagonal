package bank_account.service;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.port.in.DepositHandler;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositService implements DepositHandler {

    private final AccountLoader accountLoader;
    private final AccountUpdater accountUpdater;

    @Override
    public long deposit(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException {
        if (amount <= 0) {
            throw new IllegalAmountException("Deposit amount shall be positive");
        }
        Account account = accountLoader.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException("The account was not found");
        }
        account.addAmount(amount);
        account = accountUpdater.update(account);
        return account.getAmount();
    }
}
