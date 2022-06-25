package bank_account.adapter.in.api;

import bank_account.domain.Account;
import bank_account.port.in.AccountManager;
import bank_account.port.in.DepositHandler;
import bank_account.port.in.WithdrawalHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BankAccountController.class)
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountManager accountManagerMock;

    @MockBean
    private DepositHandler depositHandlerMock;

    @MockBean
    private WithdrawalHandler withdrawalHandler;

    @Test
    void createAccount() throws Exception {
        mockMvc.perform(post("/accounts/"))
                .andExpect(status().isCreated());
    }

    @Test
    void getAccount() throws Exception {
        Account account = new Account(1, 0, null);
        when(accountManagerMock.get(1)).thenReturn(account);
        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAccountNotFound() throws Exception {
        mockMvc.perform(get("/accounts/42000"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deposit() throws Exception {
        when(depositHandlerMock.deposit(1, 42)).thenReturn(42L);

        mockMvc.perform(post("/accounts/1/deposit/42"))
                .andExpect(status().isOk())
                .andExpect(content().string("42"));
    }

    @Test
    void withdraw() throws Exception {
        when(withdrawalHandler.withdraw(1, 42)).thenReturn(38L);

        mockMvc.perform(post("/accounts/1/withdraw/42"))
                .andExpect(status().isOk())
                .andExpect(content().string("38"));
    }
}