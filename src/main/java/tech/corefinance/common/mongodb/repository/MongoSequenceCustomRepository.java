package tech.corefinance.common.mongodb.repository;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import tech.corefinance.common.mongodb.model.MongoSequense;

import java.util.Objects;

@Repository
public class MongoSequenceCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private MongoSequenseRepository mongoSequenseRepository;

    private static Object lock = new Object();

    public long nextSequence(String seqName) {
        MongoSequense mongoSequense = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                MongoSequense.class);
        if (!Objects.isNull(mongoSequense)) {
            return mongoSequense.sequense();
        } else {
            synchronized (lock) {
                // Double DB query for multiple thread processing
                mongoSequense = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                        new Update().inc("seq",1), options().returnNew(true).upsert(true),
                        MongoSequense.class);
                if (Objects.isNull(mongoSequense)) {
                    mongoSequense = new MongoSequense(seqName, 1);
                    mongoSequenseRepository.save(mongoSequense);
                }
            }
            return mongoSequense.sequense();
        }
    }
}
