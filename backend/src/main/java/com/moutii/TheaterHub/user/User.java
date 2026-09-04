package com.moutii.TheaterHub.user;

import com.moutii.TheaterHub.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="FIRSTNAME", nullable = false)
    private String firstname;

    @Column(name="LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "EMAIL",nullable = false)
    private String email;

    @Column(name = "IS_EMAIL_VERIFIED")
    private boolean isEmailVerified = false;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "IS_PHONE_VERIFIED")
    private boolean isPhoneVerified = false;

    @Column(name="PASSWORD", nullable = false)
    private String password;

    @Column(name = "DATE_OF_BIRTH", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,CascadeType.PERSIST
    })
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = {
                    @JoinColumn(
                            name = "USER_ID",
                            nullable = false
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "ROLE_ID",
                            nullable = false
                    )
            }

    )
    private List<Role> roles;





    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "LAST_MODIFIED_AT",insertable = false)
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
