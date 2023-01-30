package com.afroze.projectmanagement.company.api.domain;

import org.springframework.data.domain.AuditorAware;
import reactor.util.annotation.NonNull;

import java.util.Optional;

public class EntityAuditorAware implements AuditorAware<Long> {
    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(1L);
    }
}
