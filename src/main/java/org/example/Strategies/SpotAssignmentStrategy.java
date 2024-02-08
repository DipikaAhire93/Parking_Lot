package org.example.Strategies;

import org.example.Models.Gate;
import org.example.Models.ParkingLot;
import org.example.Models.ParkingSlot;
import org.example.Models.VehicleType;

public interface SpotAssignmentStrategy {
    public ParkingSlot getSpot(VehicleType vehicleType);
}
