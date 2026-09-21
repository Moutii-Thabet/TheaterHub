package com.moutii.TheaterHub.moviemanagement.director;

import com.moutii.TheaterHub.moviemanagement.director.request.DirectorRequest;
import com.moutii.TheaterHub.moviemanagement.director.response.FullDirectorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class DirectorMapper {
    public Director toDirector(DirectorRequest request) {
        return Director.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }

    public void mergeDirector(Director director, DirectorRequest request){
        if(StringUtils.isNotBlank(request.getFirstname())&&
        !request.getFirstname().equals(director.getFirstname())) {
            director.setFirstname(director.getFirstname());
        }
        if(StringUtils.isNotBlank(request.getLastname())&&
                !request.getLastname().equals(director.getLastname())) {
            director.setLastname(director.getLastname());
        }

        if(StringUtils.isNotBlank(request.getDateOfBirth().toString())&&
                !request.getDateOfBirth().equals(director.getDateOfBirth())) {
            director.setDateOfBirth(director.getDateOfBirth());
        }
    }

    public FullDirectorResponse toFullDirectorResponse(Director director) {
        return FullDirectorResponse.builder()
                .id(director.getId())
                .firstname(director.getFirstname())
                .lastname(director.getLastname())
                .dateOfBirth(director.getDateOfBirth())
                .countryId(director.getOrigin().getId())
                .build();
    }
}
