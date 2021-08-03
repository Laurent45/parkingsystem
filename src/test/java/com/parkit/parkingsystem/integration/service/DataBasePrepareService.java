package com.parkit.parkingsystem.integration.service;

import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.integration.config.DataBaseTestConfig;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DataBasePrepareService {

    DataBaseTestConfig dataBaseTestConfig = new DataBaseTestConfig();

    public void clearDataBaseEntries(){
        Connection connection = null;
        PreparedStatement ps = null;
        try{
            connection = dataBaseTestConfig.getConnection();

            //set parking entries to available
            ps = connection.prepareStatement("update parking set available = true");
            ps.execute();

            //clear ticket entries;
            ps = connection.prepareStatement("truncate table ticket");
            ps.execute();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dataBaseTestConfig.closePreparedStatement(ps);
            dataBaseTestConfig.closeConnection(connection);
        }
    }

    public void addTicketIncoming() {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = dataBaseTestConfig.getConnection();
            ps = connection.prepareStatement(DBConstants.SAVE_TICKET);
            Ticket ticket = new Ticket(
                    1
                    , new ParkingSpot(1, ParkingType.CAR, false)
                    , "ABCDEF"
                    , 0
                    , LocalDateTime.now().minusHours(1)
                    , null);
            ps.setInt(1, ticket.getParkingSpot().getId());
            ps.setString(2, ticket.getVehicleRegNumber());
            ps.setDouble(3, ticket.getPrice());
            ps.setTimestamp(4, Timestamp.valueOf(
                    ticket.getInTime()));
            ps.setTimestamp(5, null);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataBaseTestConfig.closePreparedStatement(ps);
            dataBaseTestConfig.closeConnection(connection);
        }

    }


}
