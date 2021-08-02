package com.parkit.parkingsystem;

import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class InputReaderUtilTest {


    @Test
    public void givenInputInt_whenReadSelection_thenReturnInteger() {
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
        assertThat(inputReaderUtilSUT.readSelection()).isEqualTo(1);
    }

    @Test
    public void givenBadInput_whenReadSelection_thenReturnNegativeInt() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        InputReaderUtil inputReaderUtilSUT = new InputReaderUtil();
        assertThat(inputReaderUtilSUT.readSelection()).isEqualTo(-1);
    }
}
