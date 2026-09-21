package com.moutii.TheaterHub.moviemanagement.director;

import com.moutii.TheaterHub.moviemanagement.director.request.DirectorRequest;
import com.moutii.TheaterHub.moviemanagement.director.response.DirectorResponse;
import com.moutii.TheaterHub.moviemanagement.director.response.FullDirectorResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/director")
public class DirectorController {
    private final DirectorService directorService;

    @PostMapping("/add")
    @PreAuthorize("@directorSecurityService.isAdmin()")
    public ResponseEntity<Void> addDirector(
            final @Valid @RequestBody DirectorRequest request
    ) {
        this.directorService.addDirector(request);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{directorId}")
    @PreAuthorize("@directorSecurityService.isAdmin()")
    public ResponseEntity<Void> updateDirector(
            final @Valid @RequestBody DirectorRequest request,
            @PathVariable String directorId
    ) {
        this.directorService.updateDirector(request,directorId);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/directors")
    @PreAuthorize("@directorSecurityService.isAdmin()")
    public ResponseEntity<List<DirectorResponse>> getDirectors() {
        return  ResponseEntity.ok(this.directorService.getDirectors());
    }

    @GetMapping("/{directorId}")
    @PreAuthorize("@directorSecurityService.isAdmin()")
    public ResponseEntity<FullDirectorResponse> getDirector(
            final @PathVariable String directorId
    ) {
        return  ResponseEntity.ok(this.directorService.getDirectorById(directorId));
    }
}
