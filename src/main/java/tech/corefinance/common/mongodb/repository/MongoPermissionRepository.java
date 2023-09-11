package tech.corefinance.common.mongodb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoPermission;

import java.util.List;
import java.util.Optional;

@Repository
public interface MongoPermissionRepository extends MongoRepository<MongoPermission, String> {

    Optional<MongoPermission> findFirstByRoleIdAndResourceTypeAndActionAndUrlAndRequestMethod(String roleId, String resourceType, String action, String url, RequestMethod requestMethod);

    List<MongoPermission> findAllByRoleIdAndResourceType(String roleId, String resourceType, Sort sort);

    String searchByQuery = "{$or: [" + "{'roleId': { $regex: :#{#search}, $options: 'i' }}"
            + ",{'resourceType': { $regex: :#{#search}, $options: 'i' }}" + ",{'action': { $regex: :#{#search}, $options: 'i' }}"
            + ",{'url': { $regex: :#{#search}, $options: 'i' }}" + ",{'control': { $regex: :#{#search}, $options: 'i' }}"
            + ",{'requestMethod': { $regex: :#{#search}, $options: 'i' }}" + "]}";

    @Query(searchByQuery)
    Page<MongoPermission> searchBy(@Param("search") String searchText, Pageable pageRequest);

}
