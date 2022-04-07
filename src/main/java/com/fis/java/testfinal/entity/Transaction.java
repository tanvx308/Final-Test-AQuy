package com.fis.java.testfinal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@NamedQueries({
        @NamedQuery(
                name = "findTransactionsByAccountNumberAndTime",
                query = "SELECT t FROM Transaction t join t.fromAccount f join t.toAccount a  WHERE (f.accountNumber = :number or a.accountNumber = :number) and t.transactionDate between :fromDate and :toDate"
        ),
        @NamedQuery(
                name = "reportByDayBetween",
                query = "SELECT new Report (t.transactionDate, c.customerType, t.status, sum(t.amount), count (t)) FROM Transaction t join t.fromAccount f join f.customer c where t.status = :status and t.transactionDate between :fromDate and :toDate group by t.transactionDate, c.customerType, t.status"
        ),
        @NamedQuery(
                name = "reportByDay",
                query = "SELECT t, c.name, c.customerType  FROM Transaction t JOIN t.fromAccount f join f.customer c WHERE t.transactionDate between :startOfDay and :endOfDay"
        )
})
@Entity
@Table(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(name = "from_account")
    private Account fromAccount;
    @ManyToOne
    @JoinColumn(name = "to_account")
    private Account toAccount;
    @Column(nullable = false)
    private Double amount;
    private Integer status;
    private String content;
    private String errorReason;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionDate=" + transactionDate +
                ", fromAccount=" + fromAccount +
                ", toAccount=" + toAccount +
                ", amount=" + amount +
                ", status=" + status +
                ", content='" + content + '\'' +
                ", errorReason='" + errorReason + '\'' +
                '}';
    }
}
