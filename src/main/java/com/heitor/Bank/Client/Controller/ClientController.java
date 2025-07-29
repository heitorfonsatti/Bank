package com.heitor.Bank.Client.Controller;

import com.heitor.Bank.Client.DTO.ClientRecordDTO;
import com.heitor.Bank.Client.Model.ClientModel;
import com.heitor.Bank.Client.Repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/client")
    public ResponseEntity<ClientModel> saveClient(@RequestBody @Valid ClientRecordDTO clientRecordDTO) {
        var clientModel = new ClientModel();
        BeanUtils.copyProperties(clientRecordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(clientModel));
    }

    @GetMapping("/client")
    public ResponseEntity<List<ClientModel>> getAllClients() {
        List<ClientModel> clientsList = clientRepository.findAll();
        if (!clientsList.isEmpty()) {
            for (ClientModel client : clientsList) {
                UUID id = client.getIdClient();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientsList);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        client0.get().add(linkTo(methodOn(ClientController.class).getAllClients()).withRel("Clients list"));
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Object> updateClient (@PathVariable(value = "id") UUID id, @RequestBody @Valid ClientRecordDTO clientRecordDTO) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        var clientModel = client0.get();
        BeanUtils.copyProperties(clientRecordDTO, clientModel);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientModel));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<Object> deleteClient (@PathVariable(value = "id") UUID id) {
        Optional<ClientModel> client0 = clientRepository.findById(id);
        if (client0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        clientRepository.delete(client0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully");
    }

}
