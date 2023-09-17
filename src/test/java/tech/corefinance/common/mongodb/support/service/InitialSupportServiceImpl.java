package tech.corefinance.common.mongodb.support.service;

import org.springframework.stereotype.Service;
import tech.corefinance.common.service.EntityInitializer;
import tech.corefinance.common.service.InitialSupportService;

import java.util.Map;

@Service
public class InitialSupportServiceImpl implements InitialSupportService {

    @Override
    public Map<String, EntityInitializer<? extends Object>> getListInitialNamesSupported() {
        return null;
    }
}
