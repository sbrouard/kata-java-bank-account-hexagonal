package bank_account.adapter.out.persistence.data.mapping;

import bank_account.adapter.out.persistence.data.AccountEntity;
import bank_account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    /**
     * Maps the Persistence Account to the Domain Account, skipping operations.
     *
     * @param account a persitence account entity
     * @return A domain Account object
     */
    public Account mapEntityToDomainWithoutOperations(AccountEntity account) {
        return new Account(account.getId(), account.getAmount(), null);
    }

    /**
     * Maps the Domain Account to the Persistence Account without default id (to let JPA generate it)
     *
     * @param account the domain account object
     * @return a persistence account entity
     */
    public AccountEntity mapDomainToEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        if (account.getId() >= 0) { // let jpa generate the id if it does not exist yet
            accountEntity.setId(account.getId());
        }
        accountEntity.setAmount(account.getAmount());
        return accountEntity;
    }

    /**
     * Merges an updated domain account with an existing persited account entity
     *
     * @param account       the updated account
     * @param accountEntity the persisted account entity to be updated
     */
    public void merge(Account account, AccountEntity accountEntity) {
        accountEntity.setAmount(account.getAmount());
    }

}
