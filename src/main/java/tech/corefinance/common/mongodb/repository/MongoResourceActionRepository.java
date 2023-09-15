package tech.corefinance.common.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.repository.ResourceActionRepository;

@Repository
public interface MongoResourceActionRepository extends ResourceActionRepository<MongoResourceAction>,
        MongoRepository<MongoResourceAction, String> {
}
