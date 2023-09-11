package tech.corefinance.common.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "mongo_sequense")
public record MongoSequense(@Id String id, long sequense) {
    public MongoSequense() {
        this(UUID.randomUUID().toString(), 0);
    }
}