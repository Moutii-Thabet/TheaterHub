package com.moutii.TheaterHub.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @Email(message = "Invalid email")
    @NotBlank
    @Schema(example = "name@test.com")
    private String email;

    @NotBlank
    @Schema(example = "<PASSWORD>")
    private String password;

}
