package com.quantity.measurement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.quantity.measurement.LengthUnit.*;
import static com.quantity.measurement.WeightUnit.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityUC13Test {

    @Test
    @DisplayName("Verify Addition delegates through helper and maintains UC12 output")
    void testAdd_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, INCHES);
        
        Quantity<LengthUnit> sum = q1.add(q2);
        assertEquals(2.0, sum.getValue(), 0.01);
        assertEquals(FEET, sum.getUnit());

        Quantity<WeightUnit> w1 = new Quantity<>(10.0, KILOGRAM);
        Quantity<WeightUnit> w2 = new Quantity<>(5000.0, GRAM);
        Quantity<WeightUnit> sumWeight = w1.add(w2, GRAM);
        
        assertEquals(15000.0, sumWeight.getValue(), 0.01);
        assertEquals(GRAM, sumWeight.getUnit());
    }

    @Test
    @DisplayName("Verify Subtraction behavior preservation")
    void testSubtract_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, INCHES);
        
        Quantity<LengthUnit> diff = q1.subtract(q2);
        assertEquals(9.5, diff.getValue(), 0.01);
        assertEquals(FEET, diff.getUnit());
    }

    @Test
    @DisplayName("Verify Division behavior preservation and dimensionless scalar result")
    void testDivide_UC12_BehaviorPreserved() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, FEET);
        
        double scalar = q1.divide(q2);
        assertEquals(5.0, scalar, 1e-6);

        Quantity<LengthUnit> q3 = new Quantity<>(24.0, INCHES);
        assertEquals(1.0, q3.divide(q2), 1e-6);
    }

    @Test
    @DisplayName("Verify Division by Zero throws ArithmeticException")
    void testDivideByZero_ThrowsException() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

    @Test
    @DisplayName("Verify centralized validation consistency for Null operands")
    void testValidation_NullOperand_ConsistentAcrossOperations() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, FEET);

        Exception exAdd = assertThrows(IllegalArgumentException.class, () -> q1.add(null));
        Exception exSub = assertThrows(IllegalArgumentException.class, () -> q1.subtract(null));
        Exception exDiv = assertThrows(IllegalArgumentException.class, () -> q1.divide(null));

        assertEquals("Operand quantity cannot be null.", exAdd.getMessage());
        assertEquals(exAdd.getMessage(), exSub.getMessage());
        assertEquals(exSub.getMessage(), exDiv.getMessage());
    }

    @Test
    @DisplayName("Verify Cross-Category Prevention across operations")
    void testValidation_CrossCategory_ConsistentAcrossOperations() {
        Quantity<LengthUnit> qLength = new Quantity<>(10.0, FEET);
        Quantity<WeightUnit> qWeight = new Quantity<>(5.0, KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> qLength.add((Quantity) qWeight));
        assertThrows(IllegalArgumentException.class, () -> qLength.subtract((Quantity) qWeight));
        assertThrows(IllegalArgumentException.class, () -> qLength.divide((Quantity) qWeight));
    }
}