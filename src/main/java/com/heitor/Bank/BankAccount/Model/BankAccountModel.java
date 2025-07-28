package com.heitor.Bank.BankAccount.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private BigDecimal balance;
    private LocalDate createdAt;

    @Transient
    public int getDate() {
        return LocalDate.now().getYear() - this.getCreatedAt().getYear();
    }
}
