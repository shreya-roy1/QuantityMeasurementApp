public class QuantityMeasurementApp {
    public static void main(String[] args) {
        performSubtractionDemo();
        performDivisionDemo();
    }

    public static void performSubtractionDemo() {
        System.out.println("--- Subtraction Operations ---");
        GenericQuantity<LengthUnit> distance1 = new GenericQuantity<>(1.0, LengthUnit.FEET);
        GenericQuantity<LengthUnit> distance2 = new GenericQuantity<>(6.0, LengthUnit.INCH);

        // Resulting in 0.5 FEET
        System.out.println("Result: " + distance1.minus(distance2));
    }

    public static void performDivisionDemo() {
        System.out.println("\n--- Division Operations ---");
        GenericQuantity<VolumeUnit> vol1 = new GenericQuantity<>(1.0, VolumeUnit.LITRE);
        GenericQuantity<VolumeUnit> vol2 = new GenericQuantity<>(250.0, VolumeUnit.ML);

        // Resulting in 4.0 (scalar ratio)
        System.out.println("Ratio: " + vol1.ratio(vol2));
    }
}