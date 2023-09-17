package tech.corefinance.common.mongodb.services;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.mongodb.model.MongoInternalServiceConfig;
import tech.corefinance.common.mongodb.model.MongoPermission;
import tech.corefinance.common.mongodb.model.MongoResourceAction;
import tech.corefinance.common.service.AbstractPermissionService;
import tech.corefinance.common.service.LocalResourceEntityInitializer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Getter
public class MongoPermissionService extends AbstractPermissionService<MongoPermission, MongoInternalServiceConfig, MongoResourceAction> {

    protected Map<String, LocalResourceEntityInitializer<? extends Object>> listInitialNamesSupported;

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
}
