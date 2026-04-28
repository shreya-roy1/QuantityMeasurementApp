import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthExplicitAddTest {

    private static final double EPS = 1e-6;

    @Test
    void testTargetFeet() {
        var result = UC7_QuantityLengthExplicitAdd.QuantityLength.add(
                new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET),
                new UC7_QuantityLengthExplicitAdd.QuantityLength(12, UC7_QuantityLengthExplicitAdd.LengthUnit.INCH),
                UC7_QuantityLengthExplicitAdd.LengthUnit.FEET
        );

        assertTrue(result.toString().contains("2.000000"));
    }

    @Test
    void testTargetInches() {
        var result = UC7_QuantityLengthExplicitAdd.QuantityLength.add(
                new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET),
                new UC7_QuantityLengthExplicitAdd.QuantityLength(12, UC7_QuantityLengthExplicitAdd.LengthUnit.INCH),
                UC7_QuantityLengthExplicitAdd.LengthUnit.INCH
        );

        assertTrue(result.toString().contains("24.000000"));
    }

    @Test
    void testTargetYards() {
        var result = UC7_QuantityLengthExplicitAdd.QuantityLength.add(
                new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET),
                new UC7_QuantityLengthExplicitAdd.QuantityLength(12, UC7_QuantityLengthExplicitAdd.LengthUnit.INCH),
                UC7_QuantityLengthExplicitAdd.LengthUnit.YARD
        );

        assertEquals(0.666666, 
                Double.parseDouble(result.toString().split(" ")[0]), 
                1e-3);
    }

    @Test
    void testCommutativity() {
        var a = new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET);
        var b = new UC7_QuantityLengthExplicitAdd.QuantityLength(12, UC7_QuantityLengthExplicitAdd.LengthUnit.INCH);

        var r1 = UC7_QuantityLengthExplicitAdd.QuantityLength.add(a, b,
                UC7_QuantityLengthExplicitAdd.LengthUnit.YARD);

        var r2 = UC7_QuantityLengthExplicitAdd.QuantityLength.add(b, a,
                UC7_QuantityLengthExplicitAdd.LengthUnit.YARD);

        assertEquals(r1.toString(), r2.toString());
    }

    @Test
    void testNullTargetUnit() {
        assertThrows(IllegalArgumentException.class, () ->
                UC7_QuantityLengthExplicitAdd.QuantityLength.add(
                        new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET),
                        new UC7_QuantityLengthExplicitAdd.QuantityLength(1, UC7_QuantityLengthExplicitAdd.LengthUnit.FEET),
                        null
                ));
    }
}