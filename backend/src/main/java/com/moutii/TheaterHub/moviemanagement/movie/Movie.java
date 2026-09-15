package com.moutii.TheaterHub.moviemanagement.movie;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.moviemanagement.director.Director;
import com.moutii.TheaterHub.moviemanagement.genre.Genre;
import com.moutii.TheaterHub.theatremanagement.show.Show;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "MOVIE")
public class Movie extends BaseEntity {
    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="RELEASE_DATE", nullable = false)
    private LocalDate releaseDate;

    @Column(name="DURATION", nullable = false)
    private int duration;

    @Column(name="LANGUAGE", nullable = false)
    private String language;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;

    @ManyToOne
    @JoinColumn(name="DIRECTOR_ID")
    private Director director;

    @ManyToOne
    @JoinColumn(name="GENRE_ID")
    private Genre genre;
}
