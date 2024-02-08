package org.example.Strategies;

import org.example.Models.SlotAllotmentStrategyType;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotForType(
            SlotAllotmentStrategyType slotAllotmentStrategyType
    ){
        return new RandomSpotAssignmentStrategy();
    }
}
