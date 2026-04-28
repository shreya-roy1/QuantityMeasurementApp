import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC8_Test {

    private static final double EPS = 1e-6;

    @Test
    void testConvertInchToFeet() {
        double result = LengthUnit.INCH.toBaseUnit(12.0);
        assertEquals(1.0, result, EPS);
    }

    @Test
    void testConvertFeetToInch() {
        double result = LengthUnit.INCH.fromBaseUnit(1.0);
        assertEquals(12.0, result, EPS);
    }

    @Test
    void testEquality() {
        var a = new UC8_QuantityLength(1.0, LengthUnit.FEET);
        var b = new UC8_QuantityLength(12.0, LengthUnit.INCH);

        assertTrue(a.equals(b));
    }

    @Test
    void testAdditionWithTarget() {
        var result = UC8_QuantityLength.add(
                new UC8_QuantityLength(1.0, LengthUnit.FEET),
                new UC8_QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD
        );

        assertEquals(0.666666, 
            Double.parseDouble(result.toString().split(" ")[0]), 
            1e-3);
    }
}