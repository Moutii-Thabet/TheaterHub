package com.moutii.TheaterHub.moviemanagement.genre.impl;

import com.moutii.TheaterHub.exception.BusinessException;
import com.moutii.TheaterHub.exception.ErrorCode;
import com.moutii.TheaterHub.moviemanagement.genre.Genre;
import com.moutii.TheaterHub.moviemanagement.genre.GenreRepository;
import com.moutii.TheaterHub.moviemanagement.genre.GenreService;
import com.moutii.TheaterHub.moviemanagement.genre.request.GenreRequest;
import com.moutii.TheaterHub.moviemanagement.genre.response.GenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    @Override
    public void addGenre(GenreRequest request) {
        if(this.genreRepository.existsByName(request.getName())) {
            throw new BusinessException(ErrorCode.GENRE_ALREADY_EXISTS);
        }
        final Genre genre = Genre.builder()
                .name(request.getName())
                .build();
        this.genreRepository.save(genre);
    }

    @Override
    public List<GenreResponse> getGenres() {
        return this.genreRepository.findAll().stream()
                .map(genre -> GenreResponse.builder()
                        .name(genre.getName())
                        .id(genre.getId())
                        .build()
                )
                .toList();
    }
}
