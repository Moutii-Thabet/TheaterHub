package com.moutii.TheaterHub.auth;

import com.moutii.TheaterHub.auth.request.LoginRequest;
import com.moutii.TheaterHub.auth.request.RefreshTokenRequest;
import com.moutii.TheaterHub.auth.request.RegisterRequest;
import com.moutii.TheaterHub.auth.response.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication API")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login( @Valid @RequestBody final LoginRequest request) {
        return ResponseEntity.ok(this.authenticationService.login(request));
    }
    @PostMapping("/register")
    public ResponseEntity<Void> login( @Valid @RequestBody final RegisterRequest request) {
        this.authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@RequestBody final RefreshTokenRequest request) {
        return ResponseEntity.ok(this.authenticationService.refreshToken(request));
    }
}
