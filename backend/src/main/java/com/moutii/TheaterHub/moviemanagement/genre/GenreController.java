package com.moutii.TheaterHub.moviemanagement.genre;

import com.moutii.TheaterHub.moviemanagement.genre.request.GenreRequest;
import com.moutii.TheaterHub.moviemanagement.genre.response.GenreResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/genre")
@Tag(name = "Genre", description = "Genre API")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/add")
    @PreAuthorize("@genreSecurityService.isAdmin()")
    public ResponseEntity<Void> addGenre(
            @Valid
            @RequestBody
            final GenreRequest request
            ) {
        this.genreService.addGenre(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    @PreAuthorize("@genreSecurityService.isAdmin()")
    public ResponseEntity<List<GenreResponse>> getGenres() {
        return ResponseEntity.ok(this.genreService.getGenres());
    }

}
