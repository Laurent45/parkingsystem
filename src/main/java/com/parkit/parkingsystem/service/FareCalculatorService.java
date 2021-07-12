package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.commons.math3.util.Precision;

import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        long inTimeMilliSec = ticket.getInTime().getTime();
        long outTimeMilliSec = ticket.getOutTime().getTime();

        //TODO: Some tests are failing here. Need to check if this logic is correct
        float durationMin = (float) TimeUnit.MILLISECONDS.toMinutes(outTimeMilliSec - inTimeMilliSec);

        if (durationMin > 30) {
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    ticket.setPrice(Precision.round(durationMin / 60, 2) * Fare.CAR_RATE_PER_HOUR);
                    break;
                }
                case BIKE: {
                    ticket.setPrice(Precision.round(durationMin / 60, 2) * Fare.BIKE_RATE_PER_HOUR);
                    break;
                }
                default:
                    throw new IllegalArgumentException("Unkown Parking Type");
            }
        }

    }


}