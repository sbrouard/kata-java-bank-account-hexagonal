package bank_account.port.in;

import bank_account.domain.Operation;

import java.util.List;

public interface HistoryHandler {

    List<Operation> getHistory(long accountId);

}
