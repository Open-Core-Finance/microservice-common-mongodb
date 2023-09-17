package tech.corefinance.common.mongodb.support.repository;

import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.support.model.UserTest;
import tech.corefinance.common.repository.CommonResourceRepository;

@Repository
public interface UserRepository extends CommonResourceRepository<UserTest, String> {
}
