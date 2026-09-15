package com.moutii.TheaterHub.moviemanagement.director;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.moviemanagement.country.Country;
import com.moutii.TheaterHub.moviemanagement.movie.Movie;
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
@Table(name = "DIRECTOR")
public class Director extends BaseEntity {
    @Column(name="FIRSTNAME", nullable = false)
    private String firstname;

    @Column(name="LASTNAME", nullable = false)
    private String lastname;

    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "director")
    private List<Movie> movies;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_ID")
    private Country origin;


}
