import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UC4_QuantityLengthEqualityTest {

    @Test
    void testYardToYardSameValue() {
        assertTrue(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.YARD)
                .equals(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                        UC4_QuantityLengthEquality.LengthUnit.YARD)));
    }

    @Test
    void testYardToFeetEquivalent() {
        assertTrue(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.YARD)
                .equals(new UC4_QuantityLengthEquality.QuantityLength(3.0,
                        UC4_QuantityLengthEquality.LengthUnit.FEET)));
    }

    @Test
    void testYardToInchEquivalent() {
        assertTrue(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.YARD)
                .equals(new UC4_QuantityLengthEquality.QuantityLength(36.0,
                        UC4_QuantityLengthEquality.LengthUnit.INCH)));
    }

    @Test
    void testCentimeterToInchEquivalent() {
        assertTrue(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.CENTIMETER)
                .equals(new UC4_QuantityLengthEquality.QuantityLength(0.393701,
                        UC4_QuantityLengthEquality.LengthUnit.INCH)));
    }

    @Test
    void testDifferentValues() {
        assertFalse(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.YARD)
                .equals(new UC4_QuantityLengthEquality.QuantityLength(2.0,
                        UC4_QuantityLengthEquality.LengthUnit.YARD)));
    }

    @Test
    void testNullComparison() {
        assertFalse(new UC4_QuantityLengthEquality.QuantityLength(1.0,
                UC4_QuantityLengthEquality.LengthUnit.FEET).equals(null));
    }

    @Test
    void testSameReference() {
        UC4_QuantityLengthEquality.QuantityLength q =
                new UC4_QuantityLengthEquality.QuantityLength(1.0,
                        UC4_QuantityLengthEquality.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UC4_QuantityLengthEquality.QuantityLength(1.0, null);
        });
    }
}