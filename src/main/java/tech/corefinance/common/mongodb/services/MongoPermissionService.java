package tech.corefinance.common.mongodb.services;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.mongodb.model.MongoPermission;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.service.AbstractPermissionService;

@Service
@Transactional
public class MongoPermissionService extends AbstractPermissionService<MongoPermission, MongoInternalServiceConfig, MongoResourceAction> {

    @Override
    public @NotNull MongoPermission createEntityObject() {
        return new MongoPermission();
    }

    @Override
    public @NotNull MongoResourceAction newResourceAction(String resourceType, String action, String url,
                                                          RequestMethod requestMethod) {
        return new MongoResourceAction(resourceType, action, url, requestMethod);
    }
}
