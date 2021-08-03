package com.parkit.parkingsystem.constants;

public final class DBConstants {

    private DBConstants() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * SQL request that allows to get a parking number available
     * according to parking type.
     */
    public static final String GET_NEXT_PARKING_SPOT =
            "select min(PARKING_NUMBER) from parking "
            + "where AVAILABLE = true and TYPE = ?";

    /**
     * SQL request that allows to update available to a parking spot.
     */
    public static final String UPDATE_PARKING_SPOT =
            "update parking set available = ? "
            + "where PARKING_NUMBER = ?";

    /**
     * SQL request that allows to save a ticket.
     */
    public static final String SAVE_TICKET =
            "insert into ticket"
            + "(PARKING_NUMBER, "
            + "VEHICLE_REG_NUMBER, "
            + "PRICE, "
            + "IN_TIME, OUT_TIME) "
            + "values(?,?,?,?,?)";

    /**
     * SQL request that allows to update the price
     * and the out time for a ticket.
     */
    public static final String UPDATE_TICKET =
            "update ticket set PRICE=?, OUT_TIME=? "
            + "where ID=?";

    /**
     * SQL request that allows to get a ticket.
     */
    public static final String GET_TICKET =
            "select t.PARKING_NUMBER, t.ID, t.PRICE, "
            + "t.IN_TIME, t.OUT_TIME, p.TYPE "
            + "from ticket t,parking p "
            + "where p.parking_number = t.parking_number "
            + "and t.VEHICLE_REG_NUMBER=? "
            + "order by t.IN_TIME "
            + "limit 1";

    /**
     * SQL request that get the last ticket of a vehicle registration number.
     */
    public static final String GET_LAST_TICKET_VEHICLE_REG_NUMBER =
            "select ID "
            + "from ticket "
            + "where VEHICLE_REG_NUMBER = ? "
            + "limit 1";
}
