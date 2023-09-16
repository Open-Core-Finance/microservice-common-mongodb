package tech.corefinance.common.mongodb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.mongodb.model.MongoPermission;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.service.AbstractPermissionService;

import java.util.List;

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

    @Override
    protected TypeReference<List<MongoPermission>> getPermissionJsonParseType() {
        return new TypeReference<>() {
        };
    }

    @Override
    protected TypeReference<List<MongoInternalServiceConfig>> getApiConfigJsonParseType() {
        return new TypeReference<>() {
        };
    }
}
