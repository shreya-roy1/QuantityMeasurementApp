import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeasurementEqualityTest {

    @Test
    void testFeetSameValue() {
        assertTrue(new MeasurementEqualityApp.FeetMeasurement(1.0)
                .equals(new MeasurementEqualityApp.FeetMeasurement(1.0)));
    }

    @Test
    void testFeetDifferentValue() {
        assertFalse(new MeasurementEqualityApp.FeetMeasurement(1.0)
                .equals(new MeasurementEqualityApp.FeetMeasurement(2.0)));
    }

    @Test
    void testInchesSameValue() {
        assertTrue(new MeasurementEqualityApp.InchesMeasurement(1.0)
                .equals(new MeasurementEqualityApp.InchesMeasurement(1.0)));
    }

    @Test
    void testInchesDifferentValue() {
        assertFalse(new MeasurementEqualityApp.InchesMeasurement(1.0)
                .equals(new MeasurementEqualityApp.InchesMeasurement(2.0)));
    }

    @Test
    void testNullCheck() {
        assertFalse(new MeasurementEqualityApp.FeetMeasurement(1.0).equals(null));
    }

    @Test
    void testSameReference() {
        MeasurementEqualityApp.FeetMeasurement f = new MeasurementEqualityApp.FeetMeasurement(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testDifferentType() {
        MeasurementEqualityApp.FeetMeasurement f = new MeasurementEqualityApp.FeetMeasurement(1.0);
        assertFalse(f.equals("string"));
    }
}