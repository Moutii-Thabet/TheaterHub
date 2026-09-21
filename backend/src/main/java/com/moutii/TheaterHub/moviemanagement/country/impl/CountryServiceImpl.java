package com.moutii.TheaterHub.moviemanagement.country.impl;

import com.moutii.TheaterHub.exception.BusinessException;
import com.moutii.TheaterHub.exception.ErrorCode;
import com.moutii.TheaterHub.moviemanagement.country.Country;
import com.moutii.TheaterHub.moviemanagement.country.CountryRepository;
import com.moutii.TheaterHub.moviemanagement.country.CountryService;
import com.moutii.TheaterHub.moviemanagement.country.request.AddCountryRequest;
import com.moutii.TheaterHub.moviemanagement.country.response.CountryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    @Transactional
    public void addCountry(AddCountryRequest request) {
        if(this.countryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessException(ErrorCode.COUNTRY_ALREADY_EXISTS);
        }
        final Country country = Country.builder()
                .name(request.getName())
                .build();
        this.countryRepository.save(country);
    }

    @Override
    public List<CountryResponse> getCountries() {

        return this.countryRepository.findAll().stream()
                .map(country -> CountryResponse.builder()
                        .countryId(country.getId())
                        .name(country.getName())
                        .build()
                )
                .toList();
    }
}
