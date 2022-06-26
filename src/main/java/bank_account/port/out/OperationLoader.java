package bank_account.port.out;

import bank_account.domain.Operation;

import java.util.List;

public interface OperationLoader {

    /**
     * Get all operations related to an account, sorted by dated, most recent to older
     *
     * @param accountId id of the account
     * @return List of operations related to `accountId`
     */
    List<Operation> getAll(long accountId);

}
