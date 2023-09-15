package tech.corefinance.common.mongodb.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MongoPermissionServiceTest {

    private MongoPermissionService mongoPermissionService;

    @BeforeEach
    public void setUp() {
        mongoPermissionService = new MongoPermissionService();
    }

    @Test
    public void test_newPermission() {
        assertNotNull(mongoPermissionService.createEntityObject());
    }
}
