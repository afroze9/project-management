package com.afroze.projectmanagement.company.api.config;

import com.afroze.projectmanagement.company.api.domain.EntityAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {
    @Bean
    public EntityAuditorAware auditorAware() {
        return new EntityAuditorAware();
    }
}
