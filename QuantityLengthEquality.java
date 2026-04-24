package com.apps.quantitymeasurement;

public class QuantityLengthEquality {

    // Step 1: Enum for units with conversion to base unit (FEET)
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0); // 1 inch = 1/12 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Step 2: Single Quantity class (DRY principle)
    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        // equals method (core logic)
        @Override
        public boolean equals(Object obj) {

            // Reference check
            if (this == obj) return true;

            // Null + type check
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;

            // Compare after conversion
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    // Demo method
    public static void demonstrateEquality() {

        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCH);
        QuantityLength q3 = new QuantityLength(2.0, LengthUnit.FEET);

        System.out.println("UC3 - Cross Unit Equality:");
        System.out.println("1 ft == 12 inch → " + q1.equals(q2)); // true
        System.out.println("1 ft == 2 ft → " + q1.equals(q3));   // false

        QuantityLength q4 = new QuantityLength(1.0, LengthUnit.INCH);
        QuantityLength q5 = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("1 inch == 1 inch → " + q4.equals(q5)); // true
    }

    public static void main(String[] args) {
        demonstrateEquality();
    }
}