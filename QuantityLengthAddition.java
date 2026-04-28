package com.apps.quantitymeasurement;

public class QuantityLengthAddition {

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

        // ✅ Instance ADD (returns result in THIS unit)
        public QuantityLength add(QuantityLength other) {
            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null");
            }

            double sumInFeet = this.toBase() + other.toBase();
            double result = unit.fromFeet(sumInFeet);

            return new QuantityLength(result, this.unit);
        }

        // ✅ Static ADD with target unit
        public static QuantityLength add(QuantityLength a,
                                         QuantityLength b,
                                         LengthUnit targetUnit) {

            if (a == null || b == null) {
                throw new IllegalArgumentException("Operands cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double sum = a.toBase() + b.toBase();
            double result = targetUnit.fromFeet(sum);

            return new QuantityLength(result, targetUnit);
        }

        // ✅ Overloaded ADD (raw values)
        public static QuantityLength add(double v1, LengthUnit u1,
                                         double v2, LengthUnit u2,
                                         LengthUnit targetUnit) {

            return add(new QuantityLength(v1, u1),
                       new QuantityLength(v2, u2),
                       targetUnit);
        }

        // ✅ Equals (reuse UC3 logic)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toBase(), other.toBase()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    // ✅ Demo
    public static void main(String[] args) {

        System.out.println("UC6 Addition:");

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        // Instance method (result in first operand unit)
        System.out.println(a + " + " + b + " = " + a.add(b));

        // Reverse (shows unit difference)
        System.out.println(b + " + " + a + " = " + b.add(a));

        // Static method (explicit target unit)
        System.out.println("Feet result: " +
                QuantityLength.add(a, b, LengthUnit.FEET));

        System.out.println("Inch result: " +
                QuantityLength.add(a, b, LengthUnit.INCH));

        // Yards example
        QuantityLength y = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength f = new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(y + " + " + f + " = " + y.add(f));

        // CM example
        QuantityLength cm = new QuantityLength(2.54, LengthUnit.CENTIMETER);
        QuantityLength inch = new QuantityLength(1.0, LengthUnit.INCH);

        System.out.println(cm + " + " + inch + " = " + cm.add(inch));
    }
}