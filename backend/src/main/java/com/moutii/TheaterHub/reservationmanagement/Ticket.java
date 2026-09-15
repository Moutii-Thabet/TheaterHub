package com.moutii.TheaterHub.reservationmanagement;

import com.moutii.TheaterHub.common.BaseEntity;
import com.moutii.TheaterHub.theatremanagement.seat.Seat;
import com.moutii.TheaterHub.theatremanagement.show.Show;
import com.moutii.TheaterHub.usermanagement.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "TICKET")
public class Ticket extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SHOW_ID")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;
}
