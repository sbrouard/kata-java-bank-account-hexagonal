package bank_account.adapter.out.persistence;

import bank_account.adapter.out.persistence.data.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {

    List<OperationEntity> findAllByAccountIdOrderByDateDesc(long accountId);

}
