import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuantityLengthEqualityTest {

    @Test
    void testFeetToFeet_SameValue() {
        assertTrue(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                UC3_QuantityLengthEquality.LengthUnit.FEET)
                .equals(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                        UC3_QuantityLengthEquality.LengthUnit.FEET)));
    }

    @Test
    void testInchToInch_SameValue() {
        assertTrue(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                UC3_QuantityLengthEquality.LengthUnit.INCH)
                .equals(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                        UC3_QuantityLengthEquality.LengthUnit.INCH)));
    }

    @Test
    void testFeetToInch_Equivalent() {
        assertTrue(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                UC3_QuantityLengthEquality.LengthUnit.FEET)
                .equals(new UC3_QuantityLengthEquality.QuantityLength(12.0,
                        UC3_QuantityLengthEquality.LengthUnit.INCH)));
    }

    @Test
    void testInchToFeet_Equivalent() {
        assertTrue(new UC3_QuantityLengthEquality.QuantityLength(12.0,
                UC3_QuantityLengthEquality.LengthUnit.INCH)
                .equals(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                        UC3_QuantityLengthEquality.LengthUnit.FEET)));
    }

    @Test
    void testDifferentValues() {
        assertFalse(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                UC3_QuantityLengthEquality.LengthUnit.FEET)
                .equals(new UC3_QuantityLengthEquality.QuantityLength(2.0,
                        UC3_QuantityLengthEquality.LengthUnit.FEET)));
    }

    @Test
    void testNullComparison() {
        assertFalse(new UC3_QuantityLengthEquality.QuantityLength(1.0,
                UC3_QuantityLengthEquality.LengthUnit.FEET).equals(null));
    }

    @Test
    void testSameReference() {
        UC3_QuantityLengthEquality.QuantityLength q =
                new UC3_QuantityLengthEquality.QuantityLength(1.0,
                        UC3_QuantityLengthEquality.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UC3_QuantityLengthEquality.QuantityLength(1.0, null);
        });
    }
}