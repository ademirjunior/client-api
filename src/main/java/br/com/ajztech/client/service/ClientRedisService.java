package br.com.ajztech.client.service;

import br.com.ajztech.client.entity.Client;
import br.com.ajztech.client.entity.ClientRedis;
import br.com.ajztech.client.repository.ClientRedisRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@EnableScheduling
public class ClientRedisService {

    private static final long MINUTOS = 1000 * 60 * 1;
    @Autowired
    private ClientRedisRepository clientRedisRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClientService clientService;

    public ClientRedis save(ClientRedis clientRedis) {
        return clientRedisRepository.save(clientRedis);
    }

    public List<ClientRedis> findAll() {
        return (List<ClientRedis>) clientRedisRepository.findAll();
    }

    @Scheduled(fixedDelay = MINUTOS)
    public void databaseClientSync() {
        List<ClientRedis> clientRedisList = findAll();

        if (CollectionUtils.isEmpty(clientRedisList)) {
            log.info("Lista de clientes nula ou inválida");
        } else {
            List<Client> clientList = new ArrayList<>();
            clientRedisList.forEach(
                    clientRedis -> {
                        log.info(clientRedis);
                        Client client = mapper.map(clientRedis, Client.class);
                        clientList.add(client);
                    }
            );
            clientService.saveAll(clientList);
            clientRedisRepository.deleteAll(clientRedisList);
        }

    }
}
