package bank_account.adapter.out.persistence.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name = "OPERATION")
public class OperationEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AccountEntity account;

    private Long amount;

    @Column(name = "Date")
    private LocalDateTime timestamp;

}
