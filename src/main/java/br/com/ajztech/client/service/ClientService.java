package br.com.ajztech.client.service;

import br.com.ajztech.client.entity.Client;
import br.com.ajztech.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> clientList() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public void removeById(Long id) {
        clientRepository.deleteById(id);
    }

    public void saveAll(List<Client> clientList) {
        clientRepository.saveAll(clientList);
    }
}
