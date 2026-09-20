package com.moutii.TheaterHub.moviemanagement.country;

import com.moutii.TheaterHub.moviemanagement.country.request.AddCountryRequest;
import com.moutii.TheaterHub.moviemanagement.country.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CountryService {
    void addCountry(AddCountryRequest request);

    List<CountryResponse> getCountries();
}
