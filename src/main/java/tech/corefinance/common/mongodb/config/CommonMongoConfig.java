package tech.corefinance.common.mongodb.config;

import tech.corefinance.common.mongodb.converter.MongoConversionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = { "tech.corefinance.common-mongodb.repository" })
@ConditionalOnProperty(name = "tech.corefinance.app.enabled.common", havingValue = "true", matchIfMissing = true)
public class CommonMongoConfig extends MongoConfigurationSupport {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;
    @Autowired
    private List<MongoConversionSupport<?,?>> listConverter;

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(listConverter);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
}
