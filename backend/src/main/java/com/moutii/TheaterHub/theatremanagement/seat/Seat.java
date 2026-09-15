package com.moutii.TheaterHub.theatremanagement.seat;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.reservationmanagement.Ticket;
import com.moutii.TheaterHub.theatremanagement.room.Room;
import jakarta.persistence.*;
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
@Table(name = "SEAT")
public class Seat extends BaseEntity {
    @Column(name="ROW", nullable = false)
    private char row;

    @Column(name="NUMBER", nullable = false)
    private int number;

    @Column(name="TYPE")
    private String type;

    @OneToMany(mappedBy = "seat")
    private List<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;
}
