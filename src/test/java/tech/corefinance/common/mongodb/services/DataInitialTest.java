package tech.corefinance.common.mongodb.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tech.corefinance.common.mongodb.support.app.TestCommonApplication;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TestCommonApplication.class)
@ActiveProfiles({"common", "default", "unittest"})
public class DataInitialTest {

    @Autowired
    private MongoPermissionService mongoPermissionService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void test_initial_default_data() throws IOException {
        mongoPermissionService.initializationDefaultData();
    }
}
