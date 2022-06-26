package bank_account.service;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.domain.Operation;
import bank_account.port.in.DepositHandler;
import bank_account.port.in.HistoryHandler;
import bank_account.port.in.WithdrawalHandler;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.OperationLoader;
import bank_account.port.out.OperationSaver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationService implements DepositHandler, WithdrawalHandler, HistoryHandler {

    private final AccountLoader accountLoader;
    private final OperationSaver operationSaver;
    private final OperationLoader operationLoader;

    @Override
    public long deposit(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException {
        this.validateAmount(amount);
        return this.handleOperation(accountId, amount);
    }

    @Override
    public long withdraw(long accountId, long amount) throws IllegalAmountException, AccountNotFoundException {
        this.validateAmount(amount);
        return this.handleOperation(accountId, -amount);
    }

    private long handleOperation(long accountId, long amout) throws IllegalAmountException, AccountNotFoundException {
        Account account = this.getAccount(accountId);
        Operation operation = new Operation(account, amout, LocalDateTime.now());
        operation.apply();
        return operationSaver.save(operation).account().getAmount();
    }

    private void validateAmount(long amount) {
        if (amount <= 0) {
            throw new IllegalAmountException("Amount should be positive");
        }
    }

    private Account getAccount(long accountId) throws AccountNotFoundException {
        Account account = accountLoader.get(accountId);
        if (account == null) {
            throw new AccountNotFoundException("The account was not found");
        }
        return account;
    }

    @Override
    public List<Operation> getHistory(long accountId) {
        return operationLoader.getAll(accountId);
    }
}
