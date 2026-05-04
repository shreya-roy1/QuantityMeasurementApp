public class QuantityApp {

    public static void main(String[] args) {

        // Equality
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Equality: " + w1.equals(w2)); // true

        // Conversion
        QuantityWeight pounds = new QuantityWeight(2.0, WeightUnit.POUND);
        System.out.println("Convert to kg: " + pounds.convertTo(WeightUnit.KILOGRAM));

        // Addition (implicit)
        QuantityWeight sum1 = w1.add(w2);
        System.out.println("Addition (implicit): " + sum1);

        // Addition (explicit target unit)
        QuantityWeight sum2 = w1.add(w2, WeightUnit.GRAM);
        System.out.println("Addition (explicit): " + sum2);

        // Mixed units
        QuantityWeight mix = new QuantityWeight(2.0, WeightUnit.POUND)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM), WeightUnit.POUND);

        System.out.println("Mixed addition: " + mix);
    }
}