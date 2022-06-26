package bank_account.adapter.out.persistence.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Operation")
public class OperationEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private AccountEntity account;

    private Long amount;

    @Column(name = "Date")
    private LocalDateTime date;

}
