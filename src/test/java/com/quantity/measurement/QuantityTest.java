package com.quantity.measurement;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.quantity.measurement.LengthUnit.*;
import static com.quantity.measurement.WeightUnit.*;
import static com.quantity.measurement.VolumeUnit.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityTest {

    @Nested
    @DisplayName("Length Equality & Conversions (UC1 - UC5)")
    class LengthTests {
        @Test
        @DisplayName("Feet Equality (UC1)")
        void testFeetEquality() {
            Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, FEET);
            Quantity<LengthUnit> anotherFeet = new Quantity<>(1.0, FEET);
            Quantity<LengthUnit> twoFeet = new Quantity<>(2.0, FEET);

            assertEquals(oneFeet, anotherFeet);
            assertNotEquals(oneFeet, twoFeet);
            assertNotEquals(null, oneFeet);
            assertNotEquals("1.0 ft", oneFeet);
        }

        @Test
        @DisplayName("Feet and Inches Equality (UC2 & UC3)")
        void testFeetAndInchesEquality() {
            Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, FEET);
            Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, INCHES);
            Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, FEET);
            Quantity<LengthUnit> zeroInches = new Quantity<>(0.0, INCHES);

            assertEquals(oneFeet, twelveInches);
            assertEquals(zeroFeet, zeroInches);
        }

        @Test
        @DisplayName("Extended Length Units - Yard & Centimeter (UC4)")
        void testExtendedLengthUnits() {
            Quantity<LengthUnit> oneYard = new Quantity<>(1.0, YARD);
            Quantity<LengthUnit> threeFeet = new Quantity<>(3.0, FEET);
            Quantity<LengthUnit> thirtySixInches = new Quantity<>(36.0, INCHES);

            assertEquals(oneYard, threeFeet);
            assertEquals(oneYard, thirtySixInches);

            Quantity<LengthUnit> oneInch = new Quantity<>(1.0, INCHES);
            Quantity<LengthUnit> twoPointFiveFourCm = new Quantity<>(2.54, CENTIMETER);

            assertEquals(oneInch, twoPointFiveFourCm);
        }

        @Test
        @DisplayName("Unit Conversion API (UC5)")
        void testUnitConversionAPI() {
            Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, FEET);
            Quantity<LengthUnit> converted = oneFeet.convertTo(INCHES);

            assertEquals(12.0, converted.getValue());
            assertEquals(INCHES, converted.getUnit());

            Quantity<LengthUnit> oneYard = new Quantity<>(1.0, YARD);
            Quantity<LengthUnit> convertedYard = oneYard.convertTo(INCHES);
            assertEquals(36.0, convertedYard.getValue());
        }
    }

    @Nested
    @DisplayName("Weight Equality & Conversions (UC9)")
    class WeightTests {
        @Test
        @DisplayName("Weight Equality and Conversion")
        void testWeightEqualityAndConversion() {
            Quantity<WeightUnit> oneKg = new Quantity<>(1.0, KILOGRAM);
            Quantity<WeightUnit> thousandGram = new Quantity<>(1000.0, GRAM);
            Quantity<WeightUnit> onePound = new Quantity<>(1.0, POUND);
            Quantity<WeightUnit> expectedPoundKg = new Quantity<>(0.45, KILOGRAM); // 0.453592 rounded to 2 decimals is 0.45

            assertEquals(oneKg, thousandGram);
            assertEquals(expectedPoundKg, onePound.convertTo(KILOGRAM));
        }
    }

    @Nested
    @DisplayName("Volume Equality & Conversions (UC11)")
    class VolumeTests {
        @Test
        @DisplayName("Volume Equality and Conversion")
        void testVolumeEqualityAndConversion() {
            Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, GALLON);
            Quantity<VolumeUnit> threePointSevenNineLitres = new Quantity<>(3.79, LITRE); // 3.78541 rounded is 3.79

            assertEquals(oneGallon, threePointSevenNineLitres);

            Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, LITRE);
            Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, MILLILITRE);

            assertEquals(oneLitre, thousandMl);
        }
    }

    @Nested
    @DisplayName("Arithmetic Operations (UC6, UC7, UC12, UC13)")
    class ArithmeticTests {
        @Test
        @DisplayName("Addition of Lengths (UC6 & UC7)")
        void testAdditionOfLengths() {
            Quantity<LengthUnit> oneFeet = new Quantity<>(1.0, FEET);
            Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, INCHES);
            
            // Implicit target unit
            Quantity<LengthUnit> sum1 = oneFeet.add(twelveInches);
            assertEquals(2.0, sum1.getValue());
            assertEquals(FEET, sum1.getUnit());

            // Explicit target unit
            Quantity<LengthUnit> sum2 = oneFeet.add(twelveInches, INCHES);
            assertEquals(24.0, sum2.getValue());
            assertEquals(INCHES, sum2.getUnit());
        }

        @Test
        @DisplayName("Subtraction of Lengths (UC12)")
        void testSubtractionOfLengths() {
            Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, FEET);
            Quantity<LengthUnit> sixInches = new Quantity<>(6.0, INCHES);

            Quantity<LengthUnit> diff = tenFeet.subtract(sixInches);
            assertEquals(9.5, diff.getValue());
            assertEquals(FEET, diff.getUnit());
        }

        @Test
        @DisplayName("Division producing dimensionless scalar (UC12)")
        void testDivisionOfQuantities() {
            Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, FEET);
            Quantity<LengthUnit> twoFeet = new Quantity<>(2.0, FEET);

            double ratio = tenFeet.divide(twoFeet);
            assertEquals(5.0, ratio, 1e-6);

            Quantity<LengthUnit> twentyFourInches = new Quantity<>(24.0, INCHES);
            assertEquals(1.0, twentyFourInches.divide(twoFeet), 1e-6);
        }

        @Test
        @DisplayName("Division by zero throws ArithmeticException")
        void testDivisionByZero() {
            Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, FEET);
            Quantity<LengthUnit> zeroFeet = new Quantity<>(0.0, FEET);

            assertThrows(ArithmeticException.class, () -> tenFeet.divide(zeroFeet));
        }

        @Test
        @DisplayName("Weight Addition")
        void testWeightAddition() {
            Quantity<WeightUnit> tenKg = new Quantity<>(10.0, KILOGRAM);
            Quantity<WeightUnit> fiveThousandG = new Quantity<>(5000.0, GRAM);

            Quantity<WeightUnit> sum = tenKg.add(fiveThousandG, GRAM);
            assertEquals(15000.0, sum.getValue());
            assertEquals(GRAM, sum.getUnit());
        }
    }

    @Nested
    @DisplayName("Validation and Constraints (UC10)")
    class ValidationTests {
        @Test
        @DisplayName("Null checking throws IllegalArgumentException")
        void testNullHandling() {
            Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, FEET);

            assertThrows(IllegalArgumentException.class, () -> new Quantity<>(10.0, null));
            assertThrows(IllegalArgumentException.class, () -> tenFeet.add(null));
            assertThrows(IllegalArgumentException.class, () -> tenFeet.subtract(null));
            assertThrows(IllegalArgumentException.class, () -> tenFeet.divide(null));
        }

        @Test
        @DisplayName("Infinite values throw IllegalArgumentException")
        void testInfiniteValues() {
            assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.POSITIVE_INFINITY, FEET));
            assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NEGATIVE_INFINITY, FEET));
            assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, FEET));
        }

        @Test
        @DisplayName("Cross-category operations prevent mismatched units at compile time and runtime")
        @SuppressWarnings({"rawtypes", "unchecked"})
        void testCrossCategoryPrevention() {
            Quantity<LengthUnit> length = new Quantity<>(10.0, FEET);
            Quantity<WeightUnit> weight = new Quantity<>(10.0, KILOGRAM);

            assertThrows(IllegalArgumentException.class, () -> ((Quantity) length).add(weight));
            assertThrows(IllegalArgumentException.class, () -> ((Quantity) length).subtract(weight));
            assertThrows(IllegalArgumentException.class, () -> ((Quantity) length).divide(weight));
        }
    }
}
