package com.moutii.TheaterHub.auth;

import com.moutii.TheaterHub.auth.request.LoginRequest;
import com.moutii.TheaterHub.auth.request.RefreshTokenRequest;
import com.moutii.TheaterHub.auth.request.RegisterRequest;
import com.moutii.TheaterHub.auth.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);
    void register(RegisterRequest request);
    LoginResponse refreshToken(RefreshTokenRequest request);

}
