public class QuantityMeasurementApp {
    public static void main(String[] args) {
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> twelveInches = new Quantity<>(12.0, LengthUnit.INCHES);
        
        Quantity<WeightUnit> oneKg = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        
        // This works for both!
        displayEquality(oneFoot, twelveInches); 
        // displayEquality(oneFoot, oneKg); // COMPILER ERROR - Logic safe!
    }

    // Generic helper method
    public static <T extends IMeasurable> void displayEquality(Quantity<T> q1, Quantity<T> q2) {
        System.out.println("Are they equal? " + q1.equals(q2));
    }
}