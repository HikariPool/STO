package com.sto.data.config;

import com.sto.data.audit.Auditor;
import com.sto.model.entity.business.User;
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
