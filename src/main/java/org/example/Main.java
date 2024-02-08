package org.example;

import org.example.Controllers.TicketController;
import org.example.Repositories.GateRepository;
import org.example.Repositories.ParkingLotRepository;
import org.example.Repositories.TicketRepository;
import org.example.Repositories.VehicleRepository;
import org.example.Services.TicketService;

public class Main {
    public static void main(String[] args) {

        //for creating controller we need service...and for creating the services we need repository

        GateRepository gateRepository=new GateRepository();
        ParkingLotRepository parkingLotRepository=new ParkingLotRepository();
        TicketRepository ticketRepository=new TicketRepository();
        VehicleRepository vehicleRepository=new VehicleRepository();

        TicketService ticketService=new TicketService(
                        gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
                );

        TicketController ticketController=new TicketController(ticketService);
        
    }
}