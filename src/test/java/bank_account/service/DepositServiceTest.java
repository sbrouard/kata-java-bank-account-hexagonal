package bank_account.service;

import bank_account.common.exception.AccountNotFoundException;
import bank_account.common.exception.IllegalAmountException;
import bank_account.domain.Account;
import bank_account.port.out.AccountLoader;
import bank_account.port.out.AccountUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class DepositServiceTest {

    @MockBean
    private AccountLoader accountLoaderMock;
    @MockBean
    private AccountUpdater accountUpdaterMock;

    private DepositService depositService;

    @BeforeEach
    void init() {
        this.depositService = new DepositService(accountLoaderMock, accountUpdaterMock);
    }

    @Test
    void deposit() {
        Account accout = new Account(35, 100, null);
        when(accountLoaderMock.get(35)).thenReturn(accout);
        Account accoutAfterDeposit = new Account(35, 42100, null);
        when(accountUpdaterMock.update(accoutAfterDeposit)).thenReturn(accoutAfterDeposit);
        assertEquals(42100, depositService.deposit(35, 42000));
    }

    @Test
    void illegalDeposit() {
        assertThrows(IllegalAmountException.class, () -> depositService.deposit(12345, -29));
    }

    @Test
    void depositToUnknownAccount() {
        assertThrows(AccountNotFoundException.class, () -> depositService.deposit(12345, 56));
    }
}