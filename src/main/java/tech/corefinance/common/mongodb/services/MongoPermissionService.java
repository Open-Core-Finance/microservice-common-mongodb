package tech.corefinance.common.mongodb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.mongodb.model.MongoPermission;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.service.AbstractPermissionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MongoPermissionService extends AbstractPermissionService<MongoPermission, MongoInternalServiceConfig, MongoResourceAction> {

    @Override
    protected List<MongoPermission> initialPermissions(List<Resource> permissionResources) throws IOException {
        List<MongoPermission> result = new ArrayList<>(permissionResources.size());
        for (Resource resource : permissionResources) {
            var permissions = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<MongoPermission>>() {
            });
            permissions.forEach(permission -> result.add(saveOrUpdatePermission(permission)));
        }
        return result;
    }

    @Override
    protected List<MongoInternalServiceConfig> initialInternalApiConfigs(List<Resource> configResources) throws IOException {
        List<MongoInternalServiceConfig> result = new ArrayList<>(configResources.size());
        for (Resource resource : configResources) {
            var permissions = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<MongoInternalServiceConfig>>() {
            });
            permissions.forEach(config -> result.add(saveOrUpdateApiConfig(config)));
        }
        return result;
    }

    @Override
    public @NotNull MongoPermission newPermission() {
        return new MongoPermission();
    }

    @Override
    public @NotNull MongoResourceAction newResourceAction(String resourceType, String action, String url,
                                                          RequestMethod requestMethod) {
        return new MongoResourceAction(resourceType, action, url, requestMethod);
    }
}
