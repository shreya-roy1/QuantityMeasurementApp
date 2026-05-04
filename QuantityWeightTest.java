import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class QuantityWeightTest {

    @Test
    void testEquality_KgToGram() {
        assertTrue(
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .equals(new QuantityWeight(1000.0, WeightUnit.GRAM))
        );
    }

    @Test
    void testConversion_KgToPound() {
        QuantityWeight result =
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);

        assertEquals(2.20462, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight result =
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), 1e-6);
    }

    @Test
    void testAddition_ExplicitTarget() {
        QuantityWeight result =
            new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 1e-6);
    }

    @Test
    void testZeroValue() {
        QuantityWeight result =
            new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(0.0, result.getValue());
    }

    @Test
    void testNegativeValue() {
        QuantityWeight result =
            new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);

        assertEquals(-1000.0, result.getValue());
    }
}