package com.heitor.Bank.Card.Model;

import com.heitor.Bank.BankAccount.Model.BankAccountModel;
import com.heitor.Bank.Card.Utils.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.UUID;

@Entity
@Table(name = "tb_card")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardModel extends RepresentationModel<CardModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCard;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    @Enumerated(EnumType.STRING)
    private CardType type;
    private BigDecimal limit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bank_account_id")
    private BankAccountModel bankAccount;

}
