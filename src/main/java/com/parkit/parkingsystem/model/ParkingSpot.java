package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.constants.ParkingType;

public class ParkingSpot {
    /**
     * Number of parking spot.
     */
    private int number;
    /**
     * Type of parking.
     */
    private ParkingType parkingType;
    /**
     * Parking spot is available or not.
     */
    private boolean isAvailable;

    /**
     * Constructor with parameters.
     *
     * @param idParkingSpot
     * @param typeOfParking
     * @param availability
     */
    public ParkingSpot(final int idParkingSpot, final ParkingType typeOfParking,
                       final boolean availability) {
        this.number = idParkingSpot;
        this.parkingType = typeOfParking;
        this.isAvailable = availability;
    }

    /**
     * Getter number fo parking spot.
     *
     * @return number
     */
    public int getId() {
        return number;
    }

    /**
     * Getter type of parking spot.
     *
     * @return parking type
     */
    public ParkingType getParkingType() {
        return parkingType;
    }

    /**
     * Getter availability of parking spot.
     *
     * @return if available or not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Setter availability of parking spot.
     *
     * @param available
     */
    public void setAvailable(final boolean available) {
        isAvailable = available;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingSpot that = (ParkingSpot) o;
        return number == that.number;
    }

    @Override
    public final int hashCode() {
        return number;
    }
}
