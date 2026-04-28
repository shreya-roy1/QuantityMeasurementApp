import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthAdditionTest {

    private static final double EPS = 1e-6;

    @Test
    void testSameUnitFeet() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(1, UC6_QuantityLengthAddition.LengthUnit.FEET),
                new UC6_QuantityLengthAddition.QuantityLength(2, UC6_QuantityLengthAddition.LengthUnit.FEET),
                UC6_QuantityLengthAddition.LengthUnit.FEET
        );

        assertEquals(3.0, result.toString().contains("3.0") ? 3.0 : 0.0, EPS);
    }

    @Test
    void testFeetPlusInch() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(1, UC6_QuantityLengthAddition.LengthUnit.FEET),
                new UC6_QuantityLengthAddition.QuantityLength(12, UC6_QuantityLengthAddition.LengthUnit.INCH),
                UC6_QuantityLengthAddition.LengthUnit.FEET
        );

        assertTrue(result.toString().contains("2.0"));
    }

    @Test
    void testInchPlusFeet() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(12, UC6_QuantityLengthAddition.LengthUnit.INCH),
                new UC6_QuantityLengthAddition.QuantityLength(1, UC6_QuantityLengthAddition.LengthUnit.FEET),
                UC6_QuantityLengthAddition.LengthUnit.INCH
        );

        assertTrue(result.toString().contains("24.0"));
    }

    @Test
    void testYardPlusFeet() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(1, UC6_QuantityLengthAddition.LengthUnit.YARD),
                new UC6_QuantityLengthAddition.QuantityLength(3, UC6_QuantityLengthAddition.LengthUnit.FEET),
                UC6_QuantityLengthAddition.LengthUnit.YARD
        );

        assertTrue(result.toString().contains("2.0"));
    }

    @Test
    void testWithZero() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(5, UC6_QuantityLengthAddition.LengthUnit.FEET),
                new UC6_QuantityLengthAddition.QuantityLength(0, UC6_QuantityLengthAddition.LengthUnit.INCH),
                UC6_QuantityLengthAddition.LengthUnit.FEET
        );

        assertTrue(result.toString().contains("5.0"));
    }

    @Test
    void testNegative() {
        var result = UC6_QuantityLengthAddition.QuantityLength.add(
                new UC6_QuantityLengthAddition.QuantityLength(5, UC6_QuantityLengthAddition.LengthUnit.FEET),
                new UC6_QuantityLengthAddition.QuantityLength(-2, UC6_QuantityLengthAddition.LengthUnit.FEET),
                UC6_QuantityLengthAddition.LengthUnit.FEET
        );

        assertTrue(result.toString().contains("3.0"));
    }

    @Test
    void testNullOperand() {
        assertThrows(IllegalArgumentException.class, () ->
                UC6_QuantityLengthAddition.QuantityLength.add(
                        new UC6_QuantityLengthAddition.QuantityLength(1, UC6_QuantityLengthAddition.LengthUnit.FEET),
                        null,
                        UC6_QuantityLengthAddition.LengthUnit.FEET
                ));
    }
}