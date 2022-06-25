package bank_account.service;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.port.in.DepositHandler;
import bank_account.port.in.WithdrawalHandler;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationService implements DepositHandler, WithdrawalHandler {

    private final AccountLoader accountLoader;
    private final AccountUpdater accountUpdater;

    @Override
    public long deposit(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException {
        this.validateAmount(amount);
        Account account = this.getAccout(accountId);
        account.addAmount(amount);
        account = accountUpdater.update(account);
        return account.getAmount();
    }

    @Override
    public long withdraw(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException {
        this.validateAmount(amount);
        Account account = this.getAccout(accountId);
        if (account.getAmount() < amount) {
            throw new IllegalAmountException("Withdrawal amount is bigger than account amount");
        }
        account.delAmount(amount);
        account = accountUpdater.update(account);
        return account.getAmount();
    }

    private void validateAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalAmountException("Amount shall be positive");
        }
    }

    private Account getAccout(long accountId) throws AccountNotFoundException {
        Account account = accountLoader.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException("The account was not found");
        }
        return account;
    }
}
