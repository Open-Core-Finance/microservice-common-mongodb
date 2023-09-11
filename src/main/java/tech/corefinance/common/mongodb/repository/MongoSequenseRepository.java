package tech.corefinance.common.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.model.MongoSequense;
import tech.corefinance.common.repository.CommonResourceRepository;

@Repository
public interface MongoSequenseRepository extends MongoRepository<MongoSequense, String>, CommonResourceRepository<MongoSequense, String> {

}
