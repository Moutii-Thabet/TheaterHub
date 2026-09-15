package com.moutii.TheaterHub.theatremanagement.room;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.theatremanagement.seat.Seat;
import com.moutii.TheaterHub.theatremanagement.show.Show;
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
@Table(name = "ROOM")
public class Room extends BaseEntity {
    @Column(name = "CODE", nullable = false)
    private String code;

    @OneToMany(mappedBy = "room")
    private List<Show> shows;

    @OneToMany(mappedBy = "room")
    private List<Seat> seats;
}
