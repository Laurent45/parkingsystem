package com.parkit.parkingsystem.model;

import java.util.Date;

public class Ticket {
    /**
     * Integer that represents id ticket.
     */
    private int id;
    /**
     * @see ParkingSpot
     */
    private ParkingSpot parkingSpot;
    /**
     * String that represents vehicle registration number.
     */
    private String vehicleRegNumber;
    /**
     * Double that represents the price's ticket.
     */
    private double price;
    /**
     * In time fo ticket.
     *
     * @see Date
     */
    private Date inTime;
    /**
     * Out time fo ticket.
     *
     * @see Date
     */
    private Date outTime;
    /**
     * Boolean that represents if the user is a recurring user or not.
     */
    private boolean recurringUser;

    /**
     * Constructor by default.
     */
    public Ticket() {
    }

    /**
     * Constructor with parameters.
     * @param identify
     * @param spot
     * @param vehicleRegistrationNumber
     * @param priceMoney
     * @param incomingTime
     * @param exitTime
     */
    public Ticket(
            final int identify,
            final ParkingSpot spot,
            final String vehicleRegistrationNumber,
            final double priceMoney,
            final Date incomingTime,
            final Date exitTime) {
        this.id = identify;
        this.parkingSpot = spot;
        this.vehicleRegNumber = vehicleRegistrationNumber;
        this.price = priceMoney;
        this.inTime = incomingTime;
        this.outTime = exitTime;
    }

    /**
     * Getter identify.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter identify.
     * @param identify
     */
    public void setId(final int identify) {
        this.id = identify;
    }

    /**
     * Getter parking spot.
     * @return parking spot
     */
    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    /**
     * Setter parking spot.
     * @param spot
     */
    public void setParkingSpot(final ParkingSpot spot) {
        this.parkingSpot = spot;
    }

    /**
     * Getter vehicle registration number.
     * @return vehicle registration number
     */
    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    /**
     * Setter vehicle registration number.
     * @param vehicleRegistrationNumber
     */
    public void setVehicleRegNumber(final String vehicleRegistrationNumber) {
        this.vehicleRegNumber = vehicleRegistrationNumber;
    }

    /**
     * Getter price ticket.
     * @return price ticket
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter price ticket.
     * @param priceMoney
     */
    public void setPrice(final double priceMoney) {
        this.price = priceMoney;
    }

    /**
     * Getter incoming time.
     * @return incoming time
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * Setter incoming time.
     * @param incomingTime
     */
    public void setInTime(final Date incomingTime) {
        this.inTime = incomingTime;
    }

    /**
     * Getter exit time.
     * @return exit time
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * Setter exit time.
     * @param timeExit
     */
    public void setOutTime(final Date timeExit) {
        this.outTime = timeExit;
    }

    /**
     * Getter recurring user.
     * @return recurring user
     */
    public boolean isRecurringUser() {
        return recurringUser;
    }
    /**
     * Setter recurring user.
     * @param recurringUserOrNot
     */
    public void setRecurringUser(final boolean recurringUserOrNot) {
        this.recurringUser = recurringUserOrNot;
    }
}
