package com.heitor.Bank.Client.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel extends RepresentationModel<ClientModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;

    @Transient
    public int getAge() {
        return LocalDate.now().getYear() - this.getDateOfBirth().getYear();
    }
}
