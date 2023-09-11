package tech.corefinance.common.mongodb.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.corefinance.common.model.AbstractInternalServiceConfig;

@Data
@Document(collection = "internal_service_config")
@EqualsAndHashCode(callSuper = true)
public class MongoInternalServiceConfig extends AbstractInternalServiceConfig {
}
