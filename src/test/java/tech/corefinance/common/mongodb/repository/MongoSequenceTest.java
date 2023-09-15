package tech.corefinance.common.mongodb.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tech.corefinance.common.mongodb.support.app.TestCommonApplication;
import tech.corefinance.common.mongodb.support.model.CustomSequentialIdData;
import tech.corefinance.common.mongodb.support.repository.CustomSequentialIdDataRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = TestCommonApplication.class)
@ActiveProfiles({"common", "default", "unittest"})
public class MongoSequenceTest {

    @Autowired
    private CustomSequentialIdDataRepository customSequentialIdDataRepository;

    @Test
    public void test_nextSequence() {
        String name = "test data";
        var entity = new CustomSequentialIdData();
        entity.setName(name);
        entity = customSequentialIdDataRepository.save(entity);
        assertEquals(1, entity.getId());
        assertEquals(name, entity.getName());
        assertEquals(CustomSequentialIdData.class.getSimpleName(), entity.getIdSequenceName());
    }
}
