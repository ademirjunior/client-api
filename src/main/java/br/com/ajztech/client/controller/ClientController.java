package br.com.ajztech.client.controller;

import br.com.ajztech.client.entity.Client;
import br.com.ajztech.client.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Client findClientById(@PathVariable("id") Long id) {
        return clientService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientList() {
        return clientService.clientList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeClient(@PathVariable("id") Long id) {
        clientService.findById(id)
                .map(client -> {
                    clientService.removeById(client.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateClient(@PathVariable("id")Long id, @RequestBody Client client) {
        clientService.findById(id)
                .map(baseClient -> {
                    modelMapper.map(client, baseClient);
                    clientService.save(baseClient);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
