package com.moutii.TheaterHub.moviemanagement.country.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCountryRequest {
    @NotBlank(message = "name must not be blank")
    @Size(min=2, message = "minimum size of a country is 2")
    @Schema(example = "TUNISIA")
    private String name;
}
