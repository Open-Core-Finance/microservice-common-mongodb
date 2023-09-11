package tech.corefinance.common.mongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import tech.corefinance.common.model.AbstractPermission;

@Document("permission")
public class MongoPermission extends AbstractPermission {
}
