package tech.corefinance.common.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import tech.corefinance.common.model.ResourceAction;

@Document("resource_action")
public class MongoResourceAction extends ResourceAction {
}
