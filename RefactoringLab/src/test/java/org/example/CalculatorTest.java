package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void calc_shouldReturnCorrectResultForPositiveNumbers() {
        double result = calculator.sumOverProduct(2, 3);
        assertEquals((2.0 + 3.0) / (2.0 * 3.0), result, 1e-9);
    }

    @Test
    void calc_shouldReturnCorrectResultForNegativeNumbers() {
        double result = calculator.sumOverProduct(-2, 5);
        assertEquals((-2.0 + 5.0) / (-2.0 * 5.0), result, 1e-9);
    }

    @Test
    void calc_shouldReturnPositiveInfinityWhenDenominatorZeroAndNumeratorNonZero() {
        double result = calculator.sumOverProduct(0, 5);
        assertEquals(Double.POSITIVE_INFINITY, result);
    }

    @Test
    void calc_shouldReturnNaNWhenBothZero() {
        double result = calculator.sumOverProduct(0, 0);
        assertEquals(Double.NaN, result);
    }
}