package com.moutii.TheaterHub.moviemanagement.director;


import com.moutii.TheaterHub.moviemanagement.director.request.DirectorRequest;
import com.moutii.TheaterHub.moviemanagement.director.response.DirectorResponse;
import com.moutii.TheaterHub.moviemanagement.director.response.FullDirectorResponse;

import java.util.List;

public interface DirectorService {
    void addDirector(DirectorRequest request);

    List<DirectorResponse> getDirectors();

    FullDirectorResponse getDirectorById(String directorId);

    void updateDirector(DirectorRequest request, String directorId);


}
