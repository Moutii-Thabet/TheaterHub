package com.moutii.TheaterHub.theatremanagement.show;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.moviemanagement.movie.Movie;
import com.moutii.TheaterHub.reservationmanagement.Ticket;
import com.moutii.TheaterHub.theatremanagement.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "SHOW")
public class Show extends BaseEntity {
    @Column(name = "DATE_START", nullable = false)
    private LocalDate dateStart;

    @Column(name = "TIME_START", nullable = false)
    private LocalTime timeStart;

    @OneToMany(mappedBy = "show")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
}
