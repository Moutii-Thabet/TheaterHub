package com.moutii.TheaterHub.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    @NotBlank(message = "Firstname must not be empty")
    @Size(min = 2, max=20, message = "Firstname must be of size between 2 and 20 characters")
    @Schema(example = "Moutii")
    private String firstname;

    @NotBlank(message = "Lastname must not be empty")
    @Size(min = 2, max=20, message = "Lastname must be of size between 2 and 20 characters")
    @Schema(example = "Thabet")
    private String lastname;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email must not be empty")
    @Schema(example = "name@test.com")
    private String email;

    @NotBlank(message = "Phone number must not be empty")
    @Pattern(regexp = "^\\+[0-9]{10,13}$")
    @Schema(example = "+12312345678")
    private String phoneNumber;

    @NotBlank(message = "A date of birth must be provided")
    @PastOrPresent(message = "A valid date must be provided")
    @Schema(example = "05/06/2000")
    private LocalDate dateOfBirth;

    @NotBlank(message = "A password is required")
    @Schema(example = "<PASSWORD>")
    @Size(min = 8, message = "Password minimum size is 8")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*\\W).*$",message = "password must be stronger")
    private String password;

    @NotBlank(message = "You must confirm your password")
    @Size(min = 8, message = "Password minimum size is 8")
    @Schema(example = "<CONFIRM_PASSWORD>")
    private String confirmPassword;
}
