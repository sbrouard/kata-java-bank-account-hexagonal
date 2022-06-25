package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.AccountEntity;
import bank_account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    /**
     * Maps the Persistence Account to the Domain Account, skipping operations.
     *
     * @param account a parsitence account entity
     * @return A domain Account object
     */
    public Account mapEntityToDomainWithoutOperations(AccountEntity account) {
        return new Account(account.getId(), account.getAmount(), null);
    }

    public AccountEntity mapDomainToEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        if (account.getId() >= 0) { // let jpa generate the id if it does not exist yet
            accountEntity.setId(account.getId());
        }
        accountEntity.setAmount(account.getAmount());
        return accountEntity;
    }

}
