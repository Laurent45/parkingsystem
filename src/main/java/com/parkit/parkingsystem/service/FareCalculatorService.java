package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.commons.math3.util.Precision;

import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

    /**
     * 60 min per hour.
     */
    public static final int MIN_PER_HOUR = 60;

    /**
     * This methode calculate the fare according to the difference between
     * time in and time out.
     * @param ticket instance of ticket
     */
    public void calculateFare(final Ticket ticket) {
        if ((ticket.getOutTime() == null)
            || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException(
                    "Out time provided is incorrect:"
                    + ticket.getOutTime());
        }

        long inTimeMilliSec = ticket.getInTime().getTime();
        long outTimeMilliSec = ticket.getOutTime().getTime();

        //todo: Some tests are failing here.
        // Need to check if this logic is correct
        float durationMin = (float) TimeUnit.MILLISECONDS.toMinutes(
                outTimeMilliSec - inTimeMilliSec);

        if (durationMin > Fare.FREE_TIME) {
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR:
                    ticket.setPrice(
                            Precision.round(
                                    (durationMin / MIN_PER_HOUR)
                                    * Fare.CAR_RATE_PER_HOUR, 2));
                    break;
                case BIKE:
                    ticket.setPrice(
                            Precision.round(
                                    (durationMin / MIN_PER_HOUR)
                                    * Fare.BIKE_RATE_PER_HOUR, 2));
                    break;
                default:
                    throw new IllegalArgumentException("Unkown Parking Type");
            }

            if (ticket.isRecurringUser()) {
                ticket.setPrice(
                        Precision.round(
                                ticket.getPrice()
                                * Fare.DISCOUNT_RECURRING_USER, 2));
            }
        }
    }
}
