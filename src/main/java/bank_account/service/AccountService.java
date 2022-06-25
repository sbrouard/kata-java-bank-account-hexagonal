package bank_account.service;

import bank_account.domain.Account;
import bank_account.port.in.AccountManager;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountManager {

    private final AccountLoader accountLoader;
    private final AccountUpdater accountUpdater;

    @Override
    public Account get(long accountId) {
        return accountLoader.get(accountId);
    }

    @Override
    public Account create() {
        Account account = new Account();
        return accountUpdater.update(account);
    }
}
