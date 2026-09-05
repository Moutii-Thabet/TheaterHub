package com.moutii.TheaterHub.auth.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

    private String tokenType;
}
