package com.moutii.TheaterHub.moviemanagement.genre;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.moviemanagement.movie.Movie;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "GENRE")
public class Genre extends BaseEntity {
    @Column(name="NAME", nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<Movie> movies;
}
