package com.aptota.featuretoggle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.cache.CachingStateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class FeatureTogglzConfig {

    private final DataSource dataSource;

    @Bean
    public UserProvider userProvider() {
        return new UserProvider() {
            @Override
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser("admin", true);
            }
        };
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(FeatureFlags.class);
    }

    @Bean
    public StateRepository getStateRepository() {
        //return new JDBCStateRepository(dataSource, "rb_feature_toggles");
        JDBCStateRepository jdbcStateRepository = new JDBCStateRepository.Builder(dataSource)
                .tableName("rb_feature_toggles")
                .build();
        // Wrap JDBCStateRepository with CachingStateRepository
        return new CachingStateRepository(jdbcStateRepository);
    }
}
