package com.moutii.TheaterHub.moviemanagement.director.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DirectorRequest {

    @NotBlank(message = "Firstname must not be blank")
    @Size(min=2, message = "Minimum size for firstname is 2")
    @Schema(example = "Christopher")
    private String firstname;

    @NotBlank(message = "Lastname must not be blank")
    @Size(min=2, message = "Minimum size for lastname is 2")
    @Schema(example = "Nolan")
    private String lastname;

    @NotBlank(message="Date of birth must not be blank")
    @Schema(example = "08/03/2000")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Country id must not be blank")
    private String countryId;

}
