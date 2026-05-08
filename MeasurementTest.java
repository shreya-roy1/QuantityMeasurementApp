import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeasurementTest {

    @Test
    void shouldReturnTrueForEqualVolumes() {

        Measurement<VolumeType> litre =
                new Measurement<>(
                        1,
                        VolumeType.LITRE
                );

        Measurement<VolumeType> ml =
                new Measurement<>(
                        1000,
                        VolumeType.MILLILITRE
                );

        assertEquals(litre, ml);
    }

    @Test
    void shouldConvertGallonToLitre() {

        Measurement<VolumeType> gallon =
                new Measurement<>(
                        1,
                        VolumeType.GALLON
                );

        Measurement<VolumeType> litre =
                gallon.convertTo(
                        VolumeType.LITRE
                );

        assertEquals(
                3.78541,
                litre.getValue(),
                0.0001
        );
    }

    @Test
    void shouldAddVolumesCorrectly() {

        Measurement<VolumeType> litre =
                new Measurement<>(
                        1,
                        VolumeType.LITRE
                );

        Measurement<VolumeType> ml =
                new Measurement<>(
                        500,
                        VolumeType.MILLILITRE
                );

        Measurement<VolumeType> result =
                litre.add(ml);

        assertEquals(
                1.5,
                result.getValue(),
                0.0001
        );
    }

    @Test
    void shouldReturnFalseForDifferentCategories() {

        Measurement<VolumeType> litre =
                new Measurement<>(
                        1,
                        VolumeType.LITRE
                );

        Measurement<WeightType> kg =
                new Measurement<>(
                        1,
                        WeightType.KILOGRAM
                );

        assertNotEquals(litre, kg);
    }
}