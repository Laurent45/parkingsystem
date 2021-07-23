package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class TicketDAO {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LogManager.getLogger("TicketDAO");
    /**
     * @see DataBaseConfig
     */
    private DataBaseConfig dataBaseConfig = new DataBaseConfig();

    /**
     * Getter dataBaseConfig.
     * @return dataBaseConfig
     */
    public DataBaseConfig getDataBaseConfig() {
        return dataBaseConfig;
    }

    /**
     * Setter dataBaseConfig.
     * @param dBconfig
     */
    public void setDataBaseConfig(final DataBaseConfig dBconfig) {
        this.dataBaseConfig = dBconfig;
    }

    /**
     * This methode save a ticket in DB ticket.
     * @param ticket
     * @return true or false according to saving
     */
    public boolean saveTicket(final Ticket ticket) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    DBConstants.SAVE_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            //ps.setInt(1,ticket.getId());
            ps.setInt(1, ticket.getParkingSpot().getId());
            ps.setString(2, ticket.getVehicleRegNumber());
            ps.setDouble(3, ticket.getPrice());
            ps.setTimestamp(4, new Timestamp(
                    ticket.getInTime().getTime()));
            ps.setTimestamp(5, (ticket.getOutTime() == null)
                   ? null : (new Timestamp(ticket.getOutTime().getTime())));
            return ps.execute();
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
            return false;
        } finally {
            dataBaseConfig.closeConnection(con);
        }
    }

    /**
     * This methode get a ticket save in DB according to
     * vehicle registration number.
     * @param vehicleRegNumber
     * @return ticket
     */
    public Ticket getTicket(final String vehicleRegNumber) {
        Connection con = null;
        Ticket ticket = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(DBConstants.GET_TICKET);
            //ID, PARKING_NUMBER, VEHICLE_REG_NUMBER, PRICE, IN_TIME, OUT_TIME)
            ps.setString(1, vehicleRegNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ParkingSpot parkingSpot = new ParkingSpot(
                        rs.getInt(1),
                        ParkingType.valueOf(rs.getString(6)),
                        false);
                ticket.setParkingSpot(parkingSpot);
                ticket.setId(rs.getInt(2));
                ticket.setVehicleRegNumber(vehicleRegNumber);
                ticket.setPrice(rs.getDouble(3));
                ticket.setInTime(rs.getTimestamp(4));
                ticket.setOutTime(rs.getTimestamp(5));
            }
            dataBaseConfig.closeResultSet(rs);
            dataBaseConfig.closePreparedStatement(ps);
            return ticket;
        } catch (Exception ex) {
            LOGGER.error("Error fetching next available slot", ex);
            return null;
        } finally {
            dataBaseConfig.closeConnection(con);
        }
    }

    /**
     * This methode update some parameters of a ticket in DB.
     * @param ticket
     * @return true or false according to updating
     */
    public boolean updateTicket(final Ticket ticket) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    DBConstants.UPDATE_TICKET);
            ps.setDouble(1, ticket.getPrice());
            ps.setTimestamp(2, new Timestamp(
                    ticket.getOutTime().getTime()));
            ps.setInt(3, ticket.getId());
            ps.execute();
            return true;
        } catch (Exception ex) {
            LOGGER.error("Error saving ticket info", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return false;
    }

    /**
     * This methode search if a vehicle registration number is already known.
     * @param vehicleRegNumber
     * @return true or false according to the answer
     */
    public boolean searchVehicleRegistrationNumber(
            final String vehicleRegNumber) {
        Connection con = null;
        try {
            con = dataBaseConfig.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    DBConstants.GET_LAST_TICKET_VEHICLE_REG_NUMBER);
            ps.setString(1, vehicleRegNumber);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return false;
            }

        } catch (Exception ex) {
            LOGGER.error("Error while search "
                         + "if vehicleRegNumber is present in DB", ex);
        } finally {
            dataBaseConfig.closeConnection(con);
        }
        return true;
    }
}
