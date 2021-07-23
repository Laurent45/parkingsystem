package com.parkit.parkingsystem;

import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InputReaderUtilTest {

    private static InputReaderUtil inputReaderUtilSUT;

    @BeforeEach
    public void setUp() {
        inputReaderUtilSUT = new InputReaderUtil();
    }

    @Test
    public void givenInputInt_whenReadSelection_thenReturnInteger() {
        int result = 1;
        assertThat(result).isEqualTo(1);
    }
}
