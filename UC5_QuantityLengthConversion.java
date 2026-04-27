package com.apps.quantitymeasurement;

public class UC5_QuantityLengthConversion {

    // ✅ Enum (base unit = FEET)
    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    // ✅ Quantity class (same as UC4 + conversion method)
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return unit.toFeet(value);
        }

        // ✅ Instance conversion method
        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = toBaseUnit(); // convert to feet
            double converted = targetUnit.fromFeet(baseValue);

            return new QuantityLength(converted, targetUnit);
        }

        // ✅ Static conversion API
        public static double convert(double value, LengthUnit source, LengthUnit target) {

            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid numeric value");
            }

            // Normalize to base (feet)
            double base = source.toFeet(value);

            // Convert to target
            return target.fromFeet(base);
        }

        // ✅ Equality (same as UC4)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ✅ Overloaded demo methods

    // Method 1: raw values
    public static void demonstrateLengthConversion(double value,
                                                   LengthUnit from,
                                                   LengthUnit to) {
        double result = QuantityLength.convert(value, from, to);
        System.out.println(value + " " + from + " → " + result + " " + to);
    }

    // Method 2: using object
    public static void demonstrateLengthConversion(QuantityLength q,
                                                   LengthUnit to) {
        QuantityLength result = q.convertTo(to);
        System.out.println(q + " → " + result);
    }

    public static void main(String[] args) {

        System.out.println("UC5 Conversions:");

        // Static API
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCH);
        demonstrateLengthConversion(3.0, LengthUnit.YARD, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCH, LengthUnit.YARD);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETER, LengthUnit.INCH);

        // Instance API
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARD);
        demonstrateLengthConversion(q, LengthUnit.INCH);
    }
}