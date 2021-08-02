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
     * @param identify integer
     * @param spot instance of ParkingSpot
     * @param vehicleRegistrationNumber vehicle registration number
     * @param priceMoney price of ticket
     * @param incomingTime instance of Date
     * @param exitTime instance of Date
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
        this.inTime = new Date(incomingTime.getTime());
        this.outTime = new Date(exitTime.getTime());
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
     * @param identify integer
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
     * @param spot instance of ParkingSpot
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
     * @param vehicleRegistrationNumber vehicle registration number
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
     * @param priceMoney price of ticket
     */
    public void setPrice(final double priceMoney) {
        this.price = priceMoney;
    }

    /**
     * Getter incoming time.
     * @return incoming time
     */
    public Date getInTime() {
        return new Date(inTime.getTime());
    }

    /**
     * Setter incoming time.
     * @param incomingTime instance of Date
     */
    public void setInTime(final Date incomingTime) {
        this.inTime = new Date(incomingTime.getTime());
    }

    /**
     * Getter exit time.
     * @return exit time
     */
    public Date getOutTime() {
        return new Date(outTime.getTime());
    }

    /**
     * Setter exit time.
     * @param timeExit instance of Date
     */
    public void setOutTime(final Date timeExit) {
        this.outTime = new Date(timeExit.getTime());
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
     * @param recurringUserOrNot boolean
     */
    public void setRecurringUser(final boolean recurringUserOrNot) {
        this.recurringUser = recurringUserOrNot;
    }
}
