package com.linkedin.taskmanager.firsttddtest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void testDivideTwoPositiveNumbers(){
        Calculator calculator = new Calculator();
        double result = calculator.divide(6,2);
        assertEquals(3.0, result);
    }
}
