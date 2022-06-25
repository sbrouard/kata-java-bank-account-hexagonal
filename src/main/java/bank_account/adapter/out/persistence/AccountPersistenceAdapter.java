package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.AccountEntity;
import bank_account.domain.Account;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountLoader, AccountUpdater {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public Account get(long accountId) {
        Optional<AccountEntity> optionalAccountEntity = accountRepository.findById(accountId);
        return optionalAccountEntity.map(accountMapper::mapEntityToDomainWithoutOperations).orElse(null);
    }

    @Override
    public Account update(Account account) {
        AccountEntity accountEntity = accountMapper.mapDomainToEntity(account);
        accountEntity = accountRepository.save(accountEntity);
        return accountMapper.mapEntityToDomainWithoutOperations(accountEntity);
    }
}
