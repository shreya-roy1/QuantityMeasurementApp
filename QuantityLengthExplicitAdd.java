package com.apps.quantitymeasurement;

public class QuantityLengthExplicitAdd {

    // ✅ Base unit = FEET
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

    // ✅ Quantity Class
    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        // ✅ UC6 (kept for backward compatibility)
        public QuantityLength add(QuantityLength other) {
            return add(this, other, this.unit);
        }

        // ✅ UC7 (Explicit target unit)
        public static QuantityLength add(QuantityLength a,
                                         QuantityLength b,
                                         LengthUnit targetUnit) {

            if (a == null || b == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sumInFeet = a.toBase() + b.toBase();
            double result = targetUnit.fromFeet(sumInFeet);

            return new QuantityLength(result, targetUnit);
        }

        // ✅ Overloaded (raw values)
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {

            return add(new QuantityLength(v1, u1),
                       new QuantityLength(v2, u2),
                       targetUnit);
        }

        @Override
        public String toString() {
            return String.format("%.6f %s", value, unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }
    }

    // ✅ Demo
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        System.out.println("Feet: " +
                QuantityLength.add(a, b, LengthUnit.FEET));

        System.out.println("Inches: " +
                QuantityLength.add(a, b, LengthUnit.INCH));

        System.out.println("Yards: " +
                QuantityLength.add(a, b, LengthUnit.YARD));

        QuantityLength y = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength f = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println("Yard result: " +
                QuantityLength.add(y, f, LengthUnit.YARD));

        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength inch = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println("CM result: " +
                QuantityLength.add(cm, inch, LengthUnit.CENTIMETER));
    }
}