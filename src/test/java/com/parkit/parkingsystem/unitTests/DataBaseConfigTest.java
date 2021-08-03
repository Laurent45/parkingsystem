package com.parkit.parkingsystem.unitTests;

import com.parkit.parkingsystem.config.DataBaseConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class DataBaseConfigTest {

    DataBaseConfig dataBaseConfigSUT = new DataBaseConfig();

    @Test
    public void whenGetConnection_thenReturnConnection()
            throws SQLException, ClassNotFoundException {
        assertThat(dataBaseConfigSUT.getConnection()).isNotNull();
    }

    @Test
    public void whenCloseConnection_thenConnectionClosed()
            throws SQLException, ClassNotFoundException {
        Connection connection = dataBaseConfigSUT.getConnection();
        dataBaseConfigSUT.closeConnection(connection);
        assertThat(connection.isClosed()).isTrue();
    }

    @Test
    public void whenClosePrepStatement_thenPrepStatementClosed()
            throws SQLException, ClassNotFoundException {
        Connection connection = dataBaseConfigSUT.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT *");
        dataBaseConfigSUT.closePreparedStatement(ps);
        assertThat(ps.isClosed()).isTrue();
    }

    @Test
    public void whenCloseResultSet_thenResultSetClosed()
            throws SQLException, ClassNotFoundException {
        Connection connection = dataBaseConfigSUT.getConnection();
        ResultSet rs;
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Ticket")) {
            rs = ps.executeQuery();
            dataBaseConfigSUT.closePreparedStatement(ps);
        }
        dataBaseConfigSUT.closeResultSet(rs);
        assertThat(rs.isClosed()).isTrue();
    }
}
