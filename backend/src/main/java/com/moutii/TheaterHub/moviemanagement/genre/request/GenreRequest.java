package com.moutii.TheaterHub.moviemanagement.genre.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenreRequest {
    @NotBlank(message = "name must not be blank")
    @Size(min=2, message = "minimum size of a genre is 2")
    @Schema(example = "COMEDY")
    private String name;
}
