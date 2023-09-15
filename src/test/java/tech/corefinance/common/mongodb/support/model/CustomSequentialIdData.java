package tech.corefinance.common.mongodb.support.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.corefinance.common.mongodb.model.IdSequentialModel;

@Document("custom_sequential_id_data")
@Data
public class CustomSequentialIdData implements IdSequentialModel {

    @Id
    private Long id;
    private String name;
}
