import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC5_QuantityLengthConversionTest {

    private static final double EPSILON = 1e-6;

    @Test
    void testFeetToInches() {
        assertEquals(12.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(1.0,
                        UC5_QuantityLengthConversion.LengthUnit.FEET,
                        UC5_QuantityLengthConversion.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(24.0,
                        UC5_QuantityLengthConversion.LengthUnit.INCH,
                        UC5_QuantityLengthConversion.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(1.0,
                        UC5_QuantityLengthConversion.LengthUnit.YARD,
                        UC5_QuantityLengthConversion.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testCentimeterToInch() {
        assertEquals(1.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(2.54,
                        UC5_QuantityLengthConversion.LengthUnit.CENTIMETER,
                        UC5_QuantityLengthConversion.LengthUnit.INCH),
                1e-4);
    }

    @Test
    void testZeroValue() {
        assertEquals(0.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(0.0,
                        UC5_QuantityLengthConversion.LengthUnit.FEET,
                        UC5_QuantityLengthConversion.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testNegativeValue() {
        assertEquals(-12.0,
                UC5_QuantityLengthConversion.QuantityLength.convert(-1.0,
                        UC5_QuantityLengthConversion.LengthUnit.FEET,
                        UC5_QuantityLengthConversion.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                UC5_QuantityLengthConversion.QuantityLength.convert(1.0, null,
                        UC5_QuantityLengthConversion.LengthUnit.FEET));
    }

    @Test
    void testNaNValue() {
        assertThrows(IllegalArgumentException.class, () ->
                UC5_QuantityLengthConversion.QuantityLength.convert(Double.NaN,
                        UC5_QuantityLengthConversion.LengthUnit.FEET,
                        UC5_QuantityLengthConversion.LengthUnit.INCH));
    }
}