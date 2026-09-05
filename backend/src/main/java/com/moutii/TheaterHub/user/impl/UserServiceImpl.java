package com.moutii.TheaterHub.user.impl;

import com.moutii.TheaterHub.user.UserRepo;
import com.moutii.TheaterHub.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmailIgnoreCase(username)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found with value : " + username));
    }
}
