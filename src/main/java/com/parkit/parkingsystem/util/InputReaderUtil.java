package com.parkit.parkingsystem.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReaderUtil {
    /**
     * @see Scanner
     */
    private final Scanner scan = new Scanner(System.in, "UTF-8");
    /**
     * @see Logger
     */
    private static final Logger LOGGER =
            LogManager.getLogger("InputReaderUtil");

    /**
     * Read the input user.
     * @return integer input user
     */
    public int readSelection() {
        try {
            return scan.nextInt();
        } catch (InputMismatchException e) {
            LOGGER.error("Error while reading user input from Shell", e);
            System.out.println(
                    "Error reading input. "
                    + "Please enter valid number for proceeding further");
            return -1;
        }
    }

    /**
     * Read the user vehicle registration number.
     * @return String input user (vehicle registration number)
     */
    public String readVehicleRegistrationNumber() {
        try {
            String vehicleRegNumber = scan.nextLine();
            if (vehicleRegNumber == null
                || vehicleRegNumber.trim().length() == 0) {
                throw new IllegalArgumentException("Invalid input provided");
            }
            return vehicleRegNumber;
        } catch (Exception e) {
            LOGGER.error("Error while reading user input from Shell", e);
            System.out.println("Error reading input. "
                               + "Please enter a valid string "
                               + "for vehicle registration number");
            throw e;
        }
    }
}
