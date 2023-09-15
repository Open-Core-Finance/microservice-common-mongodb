package tech.corefinance.common.mongodb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import tech.corefinance.common.context.JwtContext;
import tech.corefinance.common.dto.BasicUserDto;
import tech.corefinance.common.mongodb.converter.MongoConversionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.MongoConfigurationSupport;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import tech.corefinance.common.service.JwtService;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableMongoRepositories(basePackages = { "tech.corefinance.common-mongodb.repository" })
@ConditionalOnProperty(name = "tech.corefinance.app.enabled.common", havingValue = "true", matchIfMissing = true)
@Slf4j
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

    @Bean
    public AuditorAware<BasicUserDto> userAuditorAware(JwtService jwtService) {
        log.info("Creating AuditorAware<BasicUserDto>...");
        return () -> {
            var user = jwtService.retrieveUserAsAttribute(JwtContext.getInstance().getJwt());
            if (user == null) {
                log.error("AuditorAware<BasicUserDto> retrieved null data!!");
                return Optional.empty();
            }
            log.error("AuditorAware<BasicUserDto> get user from JwtContext.");
            return Optional.of(user);
        };
    }
}
