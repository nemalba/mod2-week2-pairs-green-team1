package com.techelevator.view;

import com.techelevator.dao.JdbcParkDao;
import com.techelevator.dao.JdbcReservationDao;
import com.techelevator.dao.ParkDao;
import com.techelevator.dao.ReservationDao;
import com.techelevator.model.Park;
import com.techelevator.model.Reservation;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;
import java.util.Scanner;

public class ReservationApp {

    private ReservationDao reservationDao ;
    private ParkDao parkDao ;



    public ReservationApp(BasicDataSource dataSource) {
        parkDao = new JdbcParkDao(dataSource);
        reservationDao = new JdbcReservationDao(dataSource);

    }


    public static void main(String[] args) {


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
        dataSource.setUsername("postgres"); //Sets the username for the database.
        dataSource.setPassword("postgres1"); //Sets the password for the database.

        ReservationApp app = new ReservationApp(dataSource);

        app.run();

    }

    public void run(){

        List<Park> parks = parkDao.getAllParks();

        for(Park park: parks){
            System.out.println(park.getParkId() + " " + park.getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the park for the reservation you want to see: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Reservation> reservationList = reservationDao.getAllReservations(id);
        System.out.println(reservationList);

    }
}
