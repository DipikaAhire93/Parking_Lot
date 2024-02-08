package org.example.Services;

//multiple controllers can call Ticket services. therefore we have service been the generic.
// its good practice to have generic type input
// DTO have so many and all parameters ..and we need specific thing in services not all parameters
// we are not afraid of returning model bcoz services called by internal controller not outside client

import org.example.Exceptions.GateNotFoundException;
import org.example.Models.*;
import org.example.Repositories.GateRepository;
import org.example.Repositories.ParkingLotRepository;
import org.example.Repositories.TicketRepository;
import org.example.Repositories.VehicleRepository;
import org.example.Strategies.SpotAssignmentStrategy;
import org.example.Strategies.SpotAssignmentStrategyFactory;

import java.util.Date;
import java.util.Optional;

//gateId is a gate object ..we need to get gate information from DB therefore repositories are created
public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    //constructor

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(
            VehicleType vehicleType,
            String vehicleNumber,
            String vehicleOwnerName,
            Long gateId) throws Exception
    {
        //create ticket
        //Assign spot
        //return ticket


        Ticket ticket= new Ticket();

        //Date time
        ticket.setEntryTime(new Date());

        //gate
        Optional<Gate> gateOptional= gateRepository.findByGateId(gateId);
        if(gateOptional.isEmpty()){
            throw new GateNotFoundException();
        }

        Gate gate=gateOptional.get();
        ticket.setGate(gate);
        ticket.setOperator(gate.getOperator());

        //Vehicle
        //findVehicle ->from DB or save it to DB

        Optional<Vehicle> vehicleOptional = vehicleRepository.findVehicleByNumber(vehicleNumber);
        Vehicle saveVehicle;
        if (vehicleOptional.isEmpty()){
            Vehicle vehicle=new Vehicle();
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);
            vehicle.setNumber(vehicleNumber);

            saveVehicle =vehicleRepository.saveVehicle(vehicle);
        }else {
            saveVehicle=vehicleOptional.get();
        }


        ticket.setVehicle(saveVehicle);

        //Parking slot

        SlotAllotmentStrategyType slotAllotmentStrategyType=
                parkingLotRepository.getParkingLotFromGate(gate).getSlotAllotmentStrategyType();

        SpotAssignmentStrategy spotAssignmentStrategy=
                SpotAssignmentStrategyFactory.getSpotForType(slotAllotmentStrategyType);

        ticket.setParkingSlot(spotAssignmentStrategy.getSpot(vehicleType));

        //saving to db
        Ticket savedTicket= ticketRepository.saveTicket(ticket);

        //number

        ticket.setNumber("Ticket - " + savedTicket.getId());



        return savedTicket;
    }
}
