package com.parkit.parkingsystem;

import com.parkit.parkingsystem.service.InteractiveShell;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class App {
    private App() {
        throw new IllegalStateException("Main class");
    }
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("App");

    /**
     * Main methode about to launch app.
     *
     * @param args
     */
    public static void main(final String[] args) {
        LOGGER.info("Initializing Parking System");
        InteractiveShell.loadInterface();
    }
}
