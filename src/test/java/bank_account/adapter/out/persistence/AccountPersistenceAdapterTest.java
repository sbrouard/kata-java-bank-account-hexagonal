package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.mapping.AccountMapper;
import bank_account.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({AccountPersistenceAdapter.class, AccountMapper.class})
class AccountPersistenceAdapterTest {

    @Autowired
    AccountPersistenceAdapter accountPersistenceAdapter;

    @Test
    void get() {
        assertNotNull(accountPersistenceAdapter.get(22));
    }

    @Test
    void getNotFound() {
        assertNull(accountPersistenceAdapter.get(29));
    }

    @Test
    void update() {
        Account account = new Account(35, 200, null);
        assertEquals(account, accountPersistenceAdapter.save(account));
    }
}