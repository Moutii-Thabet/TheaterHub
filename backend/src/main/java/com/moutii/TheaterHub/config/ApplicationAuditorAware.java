package com.moutii.TheaterHub.config;

import com.moutii.TheaterHub.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class ApplicationAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null ||
        !auth.isAuthenticated() ||
        auth instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        return Optional.ofNullable(((User) auth.getPrincipal()).getId());
    }
}
