package tech.corefinance.common.mongodb.service;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.mongodb.model.MongoPermission;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.mongodb.repository.MongoInternalServiceConfigRepository;
import tech.corefinance.common.mongodb.repository.MongoPermissionRepository;
import tech.corefinance.common.repository.PermissionRepository;
import tech.corefinance.common.service.PermissionService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Getter
public class MongoPermissionService implements PermissionService<MongoPermission, MongoInternalServiceConfig, MongoResourceAction>, InitialSupportService {

    protected Map<String, LocalResourceEntityInitializer<? extends Object>> listInitialNamesSupported;
    @Autowired
    private MongoPermissionRepository permissionRepository;
    @Autowired
    private MongoInternalServiceConfigRepository internalServiceConfigRepository;

    @Override
    public PermissionRepository<MongoPermission> getRepository() {
        return permissionRepository;
    }

    @Override
    public @NotNull MongoPermission createEntityObject() {
        return new MongoPermission();
    }

    @Override
    public @NotNull MongoResourceAction newResourceAction(String resourceType, String action, String url,
                                                          RequestMethod requestMethod) {
        return new MongoResourceAction(resourceType, action, url, requestMethod);
    }

    public MongoPermissionService() {
        listInitialNamesSupported = new LinkedHashMap<>();
        listInitialNamesSupported.put("permission", new LocalResourceEntityInitializer<>(
                new TypeReference<List<MongoPermission>>() {},
                (entity, overrideIfExisted) -> initPermission(entity, overrideIfExisted)));
        listInitialNamesSupported.put("internal-api", new LocalResourceEntityInitializer<>(
                new TypeReference<List<MongoInternalServiceConfig>>() {},
                (entity, overrideIfExisted) -> initApiConfig(entity, overrideIfExisted)));
    }

    /**
     * Init permission info. Replace if existed.
     *
     * @param permission Permission to initial.
     * @param overrideIfExisted Override if existed in DB.
     * @return Permission saved in DB.
     */
    protected MongoPermission initPermission(MongoPermission permission, boolean overrideIfExisted) {
        var optional = permissionRepository.findFirstByRoleIdAndResourceTypeAndActionAndUrlAndRequestMethod(
                permission.getRoleId(),
                permission.getResourceType(), permission.getAction(), permission.getUrl(),
                permission.getRequestMethod());
        if (!optional.isPresent()) {
            return permissionRepository.save(permission);
        } else {
            var per = optional.get();
            if (overrideIfExisted) {
                per.setControl(permission.getControl());
                per = permissionRepository.save(per);
            }
            return per;
        }
    }

    /**
     * Init API Config info. Replace if existed.
     *
     * @param config API Config to initial.
     * @param overrideIfExisted Override if existed in DB.
     * @return API Config saved in DB.
     */
    protected MongoInternalServiceConfig initApiConfig(MongoInternalServiceConfig config, boolean overrideIfExisted) {
        var optional = internalServiceConfigRepository.findFirstByApiKey(config.getApiKey());
        if (!optional.isPresent()) {
            return internalServiceConfigRepository.save(config);
        } else {
            var per = optional.get();
            if (overrideIfExisted) {
                per.setServiceName(config.getServiceName());
                per.setActivated(config.isActivated());
                per = internalServiceConfigRepository.save(per);
            }
            return per;
        }
    }
}
