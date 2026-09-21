package com.moutii.TheaterHub.moviemanagement.country;

import com.moutii.TheaterHub.moviemanagement.country.request.AddCountryRequest;
import com.moutii.TheaterHub.moviemanagement.country.response.CountryResponse;
import com.moutii.TheaterHub.usermanagement.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin/country")
@RequiredArgsConstructor
@Tag(name = "Country", description = "Country API")
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/add")
    @PreAuthorize("@countrySecurityService.isAdmin()")
    public ResponseEntity<Void> addCountry(
             final @Valid @RequestBody AddCountryRequest request
    ) {
        this.countryService.addCountry(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/countries")
    @PreAuthorize("@countrySecurityService.isAdmin()")
    public ResponseEntity<List<CountryResponse>> getCountries() {
        return ResponseEntity.ok(this.countryService.getCountries());
    }

    private String getId(Authentication auth) {
        return ((User) Objects.requireNonNull(auth.getPrincipal())).getId();
    }
}
