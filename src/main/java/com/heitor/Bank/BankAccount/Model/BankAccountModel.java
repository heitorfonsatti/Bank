package com.heitor.Bank.BankAccount.Model;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int accountNumber;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int agencyNumber;
    private String type;
    private BigDecimal balance;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
