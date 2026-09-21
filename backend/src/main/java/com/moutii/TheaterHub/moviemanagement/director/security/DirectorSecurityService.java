package com.moutii.TheaterHub.moviemanagement.director.security;

import com.moutii.TheaterHub.usermanagement.role.Role;
import com.moutii.TheaterHub.usermanagement.role.RoleRepo;
import com.moutii.TheaterHub.usermanagement.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DirectorSecurityService {

    private final RoleRepo roleRepo;

    @Transactional(readOnly = true)
    public boolean isAdmin() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = (User) auth.getPrincipal();
        final Role role = this.roleRepo.findByName("ADMIN")
                .orElseThrow(()-> new EntityNotFoundException("Role not found with name ADMIN"));
        return user.getRoles().contains(role);
    }

}
