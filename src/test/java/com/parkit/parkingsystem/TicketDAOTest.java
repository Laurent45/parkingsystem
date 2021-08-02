package com.parkit.parkingsystem;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.ParkingSpot;
import com.parkit.parkingsystem.model.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketDAOTest {

    private TicketDAO ticketDAOSUT;

    @Mock
    private DataBaseConfig mockDataBaseConfig;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPrepStatement;
    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        ticketDAOSUT = new TicketDAO();
        MockitoAnnotations.initMocks(this);
        ticketDAOSUT.setDataBaseConfig(mockDataBaseConfig);
    }

    @Test
    public void givenTicket_whenSavedTicket_thenReturnTrue() throws SQLException, ClassNotFoundException {
        when(mockDataBaseConfig.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(DBConstants.SAVE_TICKET)).thenReturn(mockPrepStatement);
        when(mockPrepStatement.execute()).thenReturn(true);
        Ticket ticket = new Ticket(1, new ParkingSpot(1, ParkingType.CAR, true), "ABCDEF", 0.00, new Date(), new Date());
        boolean result = ticketDAOSUT.saveTicket(ticket);
        verify(mockPrepStatement, times(1)).setInt(anyInt(), anyInt());
        verify(mockPrepStatement, times(1)).setString(anyInt(), anyString());
        verify(mockPrepStatement, times(1)).setDouble(anyInt(), anyDouble());
        verify(mockPrepStatement, times(2)).setTimestamp(anyInt(), any());
        assertThat(result).isTrue();
    }

    @Test
    public void givenVehicleRegNumber_whenGetTicket_thenReturnTicket() throws SQLException, ClassNotFoundException {
        when(mockDataBaseConfig.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(DBConstants.GET_TICKET)).thenReturn(mockPrepStatement);
        when(mockPrepStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(anyInt())).thenReturn(1);
        when(mockResultSet.getDouble(3)).thenReturn(1.23);
        when(mockResultSet.getTimestamp(anyInt())).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(mockResultSet.getString(6)).thenReturn("CAR");

        Ticket ticketResult = ticketDAOSUT.getTicket("ABCDEF");

        assertThat(ticketResult.getId()).isEqualTo(1);
        assertThat(ticketResult.getVehicleRegNumber()).isEqualTo("ABCDEF");
        assertThat(ticketResult.getParkingSpot()).isEqualTo(new ParkingSpot(1, ParkingType.CAR, false));
        assertThat(ticketResult.getPrice()).isEqualTo(1.23);
        assertThat(ticketResult.getInTime()).isNotNull();
        assertThat(ticketResult.getOutTime()).isNotNull();
    }

    @Test
    public void givenTicket_whenUpdateTicket_thenReturnTrue() throws SQLException, ClassNotFoundException {
        when(mockDataBaseConfig.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(DBConstants.UPDATE_TICKET)).thenReturn(mockPrepStatement);
        when(mockPrepStatement.execute()).thenReturn(true);

        Ticket ticket = new Ticket(1, null, null, 1.23, new Date(2021, Calendar.JULY, 3), new Date(2021, Calendar.JULY, 4));
        boolean result = ticketDAOSUT.updateTicket(ticket);

        verify(mockPrepStatement, times(1)).setDouble(anyInt(), anyDouble());
        verify(mockPrepStatement, times(1)).setTimestamp(anyInt(), any(Timestamp.class));
        verify(mockPrepStatement, times(1)).setInt(anyInt(), anyInt());
        assertThat(result).isTrue();
    }

    @Test
    public void givenVehicleRegNumber_whenSearchVehicleRegNumber_thenReturnTrue() throws SQLException, ClassNotFoundException {
        when(mockDataBaseConfig.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(DBConstants.GET_LAST_TICKET_VEHICLE_REG_NUMBER)).thenReturn(mockPrepStatement);
        when(mockPrepStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        boolean result = ticketDAOSUT.searchVehicleRegistrationNumber("ABCDEF");

        verify(mockPrepStatement, times(1)).setString(anyInt(), anyString());
        assertThat(result).isTrue();
    }
}
