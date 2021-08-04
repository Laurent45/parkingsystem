package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import com.parkit.parkingsystem.util.InputReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

public class ParkingService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("ParkingService");

    /**
     * @see FareCalculatorService
     */
    private final FareCalculatorService fareCalculatorService =
            new FareCalculatorService();
    /**
     * @see InputReaderUtil
     */
    private final InputReaderUtil inputReaderUtil;
    /**
     * @see ParkingSpot
     */
    private final ParkingSpotDAO parkingSpotDAO;
    /**
     * @see TicketDAO
     */
    private final TicketDAO ticketDAO;

    /**
     * Constructor with parameters.
     *
     * @param inputReaderUtilInstance instance of InputReaderUtil
     * @param parkingSpotDAOInstance instance of ParkingSpotDAO
     * @param ticketDAOInstance instance of TicketDAO
     */
    public ParkingService(final InputReaderUtil inputReaderUtilInstance,
                          final ParkingSpotDAO parkingSpotDAOInstance,
                          final TicketDAO ticketDAOInstance) {
        this.inputReaderUtil = inputReaderUtilInstance;
        this.parkingSpotDAO = parkingSpotDAOInstance;
        this.ticketDAO = ticketDAOInstance;
    }

    /**
     * This methode allows to save a new ticket in DB when
     * a vehicle incoming.
     */
    public void processIncomingVehicle() {
        try {
            ParkingSpot parkingSpot = getNextParkingNumberIfAvailable();
            if (parkingSpot != null && parkingSpot.getId() > 0) {
                String vehicleRegNumber = getVehichleRegNumber();
                parkingSpot.setAvailable(false);
                //allot this parking space and mark it's availability as false
                parkingSpotDAO.updateParking(parkingSpot);

                LocalDateTime inTime = LocalDateTime.now();
                Ticket ticket = new Ticket();
                ticket.setParkingSpot(parkingSpot);
                ticket.setVehicleRegNumber(vehicleRegNumber);
                ticket.setPrice(0);
                ticket.setInTime(inTime);
                ticket.setRecurringUser(
                        ticketDAO.searchVehicleRegistrationNumber(
                                vehicleRegNumber));
                if (ticket.isRecurringUser()) {
                    System.out.println(
                            "Welcome back! As a recurring user"
                            + " of our parking lot, you'll benefit "
                            + "from a 5% discount.");
                }

                ticketDAO.saveTicket(ticket);
                System.out.println("Generated Ticket and saved in DB");
                System.out.println(
                        "Please park your vehicle in spot number:"
                        + parkingSpot.getId());
                System.out.println(
                        "Recorded in-time for vehicle number:"
                        + vehicleRegNumber + " is:" + inTime);
            }
        } catch (Exception e) {
            LOGGER.error("Unable to process incoming vehicle", e);
        }
    }

    private String getVehichleRegNumber() {
        System.out.println(
                "Please type the vehicle registration number "
                + "and press enter key");
        return inputReaderUtil.readVehicleRegistrationNumber();
    }

    /**
     * This methode verify if a parking spot is available.
     * @return parkingSpot
     */
    public ParkingSpot getNextParkingNumberIfAvailable() {
        int parkingNumber;
        ParkingSpot parkingSpot = null;
        try {
            ParkingType parkingType = getVehichleType();
            parkingNumber = parkingSpotDAO.getNextAvailableSlot(parkingType);
            if (parkingNumber > 0) {
                parkingSpot = new ParkingSpot(
                        parkingNumber,
                        parkingType,
                        true);
            } else {
                throw new Exception(
                        "Error fetching parking number from DB. "
                        + "Parking slots might be full");
            }
        } catch (IllegalArgumentException ie) {
            LOGGER.error("Error parsing user input for type of vehicle", ie);
        } catch (Exception e) {
            LOGGER.error("Error fetching next available parking slot", e);
        }
        return parkingSpot;
    }

    private ParkingType getVehichleType() {
        System.out.println("Please select vehicle type from menu");
        System.out.println("1 CAR");
        System.out.println("2 BIKE");
        int input = inputReaderUtil.readSelection();
        switch (input) {
            case 1:
                return ParkingType.CAR;
            case 2:
                return ParkingType.BIKE;
            default:
                System.out.println("Incorrect input provided");
                throw new IllegalArgumentException("Entered input is invalid");
        }
    }

    /**
     * This methode call FareCalculatorService in order to calculate the fare
     * and update ticket and DB parking.
     */
    public void processExitingVehicle() {
        try {
            String vehicleRegNumber = getVehichleRegNumber();
            Ticket ticket = ticketDAO.getTicket(vehicleRegNumber);
            LocalDateTime outTime = LocalDateTime.now();
            ticket.setOutTime(outTime);
            fareCalculatorService.calculateFare(ticket);
            if (ticketDAO.updateTicket(ticket)) {
                ParkingSpot parkingSpot = ticket.getParkingSpot();
                parkingSpot.setAvailable(true);
                parkingSpotDAO.updateParking(parkingSpot);
                System.out.println(
                        "Please pay the parking fare:" + ticket.getPrice());
                System.out.println(
                        "Recorded out-time for vehicle number:"
                        + ticket.getVehicleRegNumber() + " is:" + outTime);
            } else {
                System.out.println(
                        "Unable to update ticket information. Error occurred");
            }
        } catch (Exception e) {
            LOGGER.error("Unable to process exiting vehicle", e);
        }
    }
}
