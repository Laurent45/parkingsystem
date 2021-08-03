package com.parkit.parkingsystem.unitTests;

import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class InputReaderUtilTest {


    @Test
    public void givenInputInt_whenReadSelection_thenReturnInteger() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
        assertThat(inputReaderUtilSUT.readSelection()).isEqualTo(1);
    }

    @Test
    public void givenBadInput_whenReadSelection_thenReturnNegativeInt() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
        assertThat(inputReaderUtilSUT.readSelection()).isEqualTo(-1);
    }

    @Test
    public void givenVehicleRegNumber_whenReadVehicleNumb_thenReturnString() {
        String input = "AB-CD-534";
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
        assertThat(inputReaderUtilSUT.readVehicleRegistrationNumber())
                .isEqualTo("AB-CD-534");
    }

    @Test
    public void givenNothing_whenReadVehicleNumb_thenReturnString() {
        InputStream in = new ByteArrayInputStream("".getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        assertThatThrownBy(() -> {
            InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
            inputReaderUtilSUT.readVehicleRegistrationNumber();
        }).isInstanceOf(Exception.class);
    }
}
