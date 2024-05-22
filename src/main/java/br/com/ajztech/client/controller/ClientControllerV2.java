package br.com.ajztech.client.controller;

import br.com.ajztech.client.entity.ClientRedis;
import br.com.ajztech.client.service.ClientRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/client")
public class ClientControllerV2 {

    @Autowired
    private ClientRedisService clientRedisService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientRedis save(@RequestBody ClientRedis clientRedis) {
        return clientRedisService.save(clientRedis);
    }

    @GetMapping
    public List<ClientRedis> findAll() {
        return clientRedisService.findAll();
    }
}
