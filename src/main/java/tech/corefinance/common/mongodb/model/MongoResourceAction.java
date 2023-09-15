package tech.corefinance.common.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.RequestMethod;
import tech.corefinance.common.model.AbstractResourceAction;

@Document("resource_action")
public class MongoResourceAction extends AbstractResourceAction {
    public MongoResourceAction(String resourceType, String action, String url, RequestMethod requestMethod) {
        super(resourceType, action, url, requestMethod);
    }
}
