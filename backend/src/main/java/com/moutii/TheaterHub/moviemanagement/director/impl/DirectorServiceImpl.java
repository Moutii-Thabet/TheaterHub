package com.moutii.TheaterHub.moviemanagement.director.impl;

import com.moutii.TheaterHub.exception.BusinessException;
import com.moutii.TheaterHub.exception.ErrorCode;
import com.moutii.TheaterHub.moviemanagement.country.Country;
import com.moutii.TheaterHub.moviemanagement.country.CountryRepository;
import com.moutii.TheaterHub.moviemanagement.country.response.CountryResponse;
import com.moutii.TheaterHub.moviemanagement.director.Director;
import com.moutii.TheaterHub.moviemanagement.director.DirectorMapper;
import com.moutii.TheaterHub.moviemanagement.director.DirectorRepository;
import com.moutii.TheaterHub.moviemanagement.director.DirectorService;
import com.moutii.TheaterHub.moviemanagement.director.request.DirectorRequest;
import com.moutii.TheaterHub.moviemanagement.director.response.DirectorResponse;
import com.moutii.TheaterHub.moviemanagement.director.response.FullDirectorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    private final DirectorMapper directorMapper;

    private final CountryRepository countryRepository;

    @Override
    public void addDirector(DirectorRequest request) {
        if(this.directorRepository.existsByFirstnameIgnoreCaseAndLastnameIgnoreCase(request.getFirstname(), request.getLastname())) {
            throw new BusinessException(ErrorCode.DIRECTOR_ALREADY_EXISTS);
        }
        final Director director = this.directorMapper.toDirector(request);
        final Country country = this.countryRepository.findById(request.getCountryId())
                .orElseThrow(()->new EntityNotFoundException("Country not found with id : " + request.getCountryId()));
        director.setOrigin(country);
        this.directorRepository.save(director);
    }

    @Override
    public void updateDirector(DirectorRequest request, String directorId) {
        final Director director = this.directorRepository.findById(directorId)
                .orElseThrow(()-> new EntityNotFoundException("Director not found with id : " + directorId));
        this.directorMapper.mergeDirector(director,request);
        final Country country = this.countryRepository.findById(request.getCountryId())
                .orElseThrow(()-> new EntityNotFoundException("Country not found with id : " + request.getCountryId()));
        director.setOrigin(country);
        this.directorRepository.save(director);

    }

    @Override
    public List<DirectorResponse> getDirectors() {
        return this.directorRepository.findAll().stream()
                .map(director -> DirectorResponse.builder()
                        .id(director.getId())
                        .firstname(director.getFirstname())
                        .lastname(director.getLastname())
                        .build())
                .toList();
    }

    @Override
    public FullDirectorResponse getDirectorById(String directorId) {
        final Director director = this.directorRepository.findById(directorId)
                .orElseThrow(()-> new EntityNotFoundException("Director not found with id : " + directorId));
        return this.directorMapper.toFullDirectorResponse(director) ;
    }


}
