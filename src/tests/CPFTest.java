package tests;

import main.CPF;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CPFTest {
    CPF sut = new CPF();

    @Test
    void Should_ReturnFalse_When_CpfIsNull() {
        boolean result = sut.validate(null);

        assertFalse(result);
    }

    @Test
    void Should_ReturnFalse_When_CpfIsEmpty() {
        boolean result = sut.validate("");

        assertFalse(result);
    }

    @Test
    void Should_ReturnFalse_When_CpfIsBlank() {
        boolean result = sut.validate(" ");

        assertFalse(result);
    }

    @Test
    void Should_ReturnFalse_When_CpfHasInvalidLength() {
        boolean result = sut.validate("8888888888");

        assertFalse(result);
    }

    @Test
    void Should_ReturnTrue_When_CpfIsValid() {
        List<String> validCpf = new ArrayList<String>(Arrays.asList("987.654.321-00", "98765432100", "714.602.380-01",
                "71460238001", "313.030.210-72", "31303021072", "144.796.170-60", "14479617060"));

        validCpf.forEach(cpf -> {
            boolean result = sut.validate(cpf);

            assertTrue(result);
        });
    }

    @Test
    void Should_ReturnFalse_When_CpfIsInvalid() {
        List<String> invalidCpf = new ArrayList<String>(
                Arrays.asList("111.111.111-11", "222.222.222-22", "333.333.333-33", "44444444444"));

        invalidCpf.forEach(cpf -> {
            boolean result = sut.validate(cpf);

            assertFalse(result);
        });
    }
}
