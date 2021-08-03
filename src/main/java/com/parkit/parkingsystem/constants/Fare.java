package com.parkit.parkingsystem.constants;

public final class Fare {
    private Fare() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Rate per hour for parking a bike.
     */
    public static final double BIKE_RATE_PER_HOUR = 1.0;
    /**
     * Rate per hour for parking a car.
     */
    public static final double CAR_RATE_PER_HOUR = 1.5;
    /**
     * Time max free.
     */
    public static final int FREE_TIME = 30;
    /**
     * Reduce for recurring user.
     */
    public static final double DISCOUNT_RECURRING_USER = 0.95;
}
