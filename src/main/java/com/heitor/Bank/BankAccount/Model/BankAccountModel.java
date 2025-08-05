package com.heitor.Bank.BankAccount.Model;

import com.heitor.Bank.BankAccount.Utils.BankAccountType;
import com.heitor.Bank.Card.Model.CardModel;
import com.heitor.Bank.Client.Model.ClientModel;
import com.heitor.Bank.Transaction.Model.TransactionModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountModel extends RepresentationModel<BankAccountModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idAccount;
    private int accountNumber;
    private int agencyNumber;

    @Enumerated(EnumType.STRING)
    private BankAccountType type;
    private BigDecimal balance;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientModel client;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<CardModel> cards = new ArrayList<>();

    @OneToMany(mappedBy = "sourceAccount")
    private List<TransactionModel> sentTransactions = new ArrayList<>();

    @OneToMany(mappedBy = "destinationAccount")
    private List<TransactionModel> receivedTransactions = new ArrayList<>();

}
