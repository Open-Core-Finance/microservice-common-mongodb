package tech.corefinance.common.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.repository.InternalServiceConfigRepository;

@Repository
public interface MongoInternalServiceConfigRepository extends MongoRepository<MongoInternalServiceConfig, String>,
        InternalServiceConfigRepository<MongoInternalServiceConfig> {
}
