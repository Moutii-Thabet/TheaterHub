package com.moutii.TheaterHub.moviemanagement.director.response;

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
public class FullDirectorResponse {

    private String id;


    private String firstname;


    private String lastname;


    private LocalDate dateOfBirth;

    private String countryId;
}
