package tech.corefinance.common.mongodb.support.repository;

import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.support.model.RoleTest;
import tech.corefinance.common.repository.CommonResourceRepository;

@Repository
public interface RoleRepository extends CommonResourceRepository<RoleTest, String> {
}
