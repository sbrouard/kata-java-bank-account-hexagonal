package bank_account.adapter.out.persistence.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long amount;

    @OneToMany
    private List<OperationEntity> operations;

}
