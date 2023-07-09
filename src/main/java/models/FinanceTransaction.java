package models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*; // поставимо * - все з javax.persistence
import java.util.Date;

@Entity  // Аннотація @Entity в Hibernate вказує, що клас є сутністю, яка має бути збережена в базі даних.
//@Table(name = "finance-transaction")  // так можна називати таблицю в БД
@Data
@NoArgsConstructor

public class FinanceTransaction {

    // Створимо первинний ключ @Id i @GeneratedValue(і++) + змінну id! (помилка зникне!)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "transaction_id") // перейменування колонки в БД
    private int id;

    @Enumerated(EnumType.STRING)   // в Hibernate використовується для вказівки, як перерахувані типи (enum) повинні бути збережені в базі даних.
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private Status status;

    private double sum;

    @CreationTimestamp   // Аннотація використовується для автоматичного створення значення часу при створенні запису в базі даних.
    @Temporal(TemporalType.TIMESTAMP) // час створення - часова мітка
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private boolean isDone;

    @Type(type = "text")
  //@Column (length = 65535)
    private String paymentTxt;

    public FinanceTransaction(TransactionType transactionType, Status status, double sum, boolean isDone, String paymentTxt) {
        this.transactionType = transactionType;
        this.status = status;
        this.sum = sum;
        this.isDone = isDone;
        this.paymentTxt = paymentTxt;
    }
}
