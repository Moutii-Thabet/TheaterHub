package com.moutii.TheaterHub.auth.impl;

import com.moutii.TheaterHub.auth.AuthenticationService;
import com.moutii.TheaterHub.auth.request.LoginRequest;
import com.moutii.TheaterHub.auth.request.RefreshTokenRequest;
import com.moutii.TheaterHub.auth.request.RegisterRequest;
import com.moutii.TheaterHub.auth.response.LoginResponse;
import com.moutii.TheaterHub.exception.BusinessException;
import com.moutii.TheaterHub.exception.ErrorCode;
import com.moutii.TheaterHub.role.Role;
import com.moutii.TheaterHub.role.RoleRepo;
import com.moutii.TheaterHub.security.JwtService;
import com.moutii.TheaterHub.user.User;
import com.moutii.TheaterHub.user.UserMapper;
import com.moutii.TheaterHub.user.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    final private RoleRepo roleRepo;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
                );

        User user = (User) auth.getPrincipal();
        String username = user.getUsername();
        String accessToken = this.jwtService.generateAccessToken(username);
        String refreshToken = this.jwtService.generateRefreshToken(username);
        String tokenType = "Bearer";
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType(tokenType)
                .build();
        
    }

    @Override
    @Transactional(readOnly = true)
    public void register(RegisterRequest request) {
        checkEmail(request.getEmail());
        checkPhoneNumber(request.getPhoneNumber());
        checkPasswords(request.getPassword(), request.getConfirmPassword());
        checkDateOfBirth(request.getDateOfBirth());
        User user = this.userMapper.toUser(request);
        Role role = this.roleRepo.findByName("USER")
                .orElseThrow(()-> new EntityNotFoundException("Role not found with name : USER"));
        user.setRoles(List.of(role));
        this.userRepo.save(user);
    }




    @Override
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        final String newAccessToken = this.jwtService.refreshAccessToken(request.getRefreshToken());
        final String tokenType = "Bearer";
        return LoginResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(request.getRefreshToken())
                .tokenType(tokenType)
                .build();
    }



    private void checkEmail(String email) {
        if(this.userRepo.existsByEmailIgnoreCase(email)) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
    }

    private void checkPhoneNumber(String phoneNumber) {
        if(this.userRepo.existsByPhoneNumber(phoneNumber)) {
            throw new BusinessException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
    }

    private void checkPasswords(String password, String confirmPassword) {
        if(password==null || !password.equals(confirmPassword)) {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
    }

    private void checkDateOfBirth(LocalDate dateOfBirth) {
        if(LocalDate.now().minusYears(18).isBefore(dateOfBirth)) {
            throw new BusinessException(ErrorCode.NOT_OLD_ENOUGH);
        }
    }
}
