package com.flat.data.config;

import com.flat.data.audit.Auditor;
import com.flat.model.entity.business.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
    @Bean
    public AuditorAware<User> auditorAware() {
        return new Auditor();
    }
}
