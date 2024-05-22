package br.com.ajztech.client.repository;

import br.com.ajztech.client.entity.ClientRedis;
import org.springframework.data.repository.CrudRepository;

public interface ClientRedisRepository extends CrudRepository<ClientRedis, String> {
}
