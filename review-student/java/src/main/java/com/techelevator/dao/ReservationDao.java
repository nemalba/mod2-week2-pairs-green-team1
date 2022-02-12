package com.techelevator.dao;

import com.techelevator.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    List<Reservation> getAllReservations(int parkId);
    Reservation getReservation(int reservationId);
    int createReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate);

}
