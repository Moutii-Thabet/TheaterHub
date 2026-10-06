package com.moutii.TheaterHub.moviemanagement.genre;


import com.moutii.TheaterHub.moviemanagement.genre.request.GenreRequest;
import com.moutii.TheaterHub.moviemanagement.genre.response.GenreResponse;

import java.util.List;

public interface GenreService {

    void addGenre(GenreRequest request);

    List<GenreResponse> getGenres();

}
