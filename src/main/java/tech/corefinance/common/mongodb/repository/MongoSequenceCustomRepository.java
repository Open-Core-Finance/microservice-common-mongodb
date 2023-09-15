package tech.corefinance.common.mongodb.repository;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.model.MongoSequence;

import java.util.Objects;

@Repository
public class MongoSequenceCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private MongoSequenseRepository mongoSequenseRepository;

    private static Object lock = new Object();

    public long nextSequence(String seqName) {
        MongoSequence mongoSequence = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("sequence",1), options().returnNew(false).upsert(true),
                MongoSequence.class);
        if (Objects.isNull(mongoSequence) || Objects.isNull(mongoSequence.sequence())) {
            synchronized (lock) {
                // Double DB query for multiple thread processing
                mongoSequence = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                        new Update().inc("sequence", 1), options().returnNew(true).upsert(true),
                        MongoSequence.class);
                if (Objects.isNull(mongoSequence) || Objects.isNull(mongoSequence.sequence())) {
                    mongoSequence = new MongoSequence(seqName, 1L);
                    mongoSequenseRepository.save(mongoSequence);
                }
            }
        }
        return mongoSequence.sequence();
    }
}
