package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (getClass() != obj.getClass()) {
                return false;
            }

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {

        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        Feet f3 = new Feet(2.0);

        System.out.println("1.0 ft vs 1.0 ft: " + f1.equals(f2)); // true
        System.out.println("1.0 ft vs 2.0 ft: " + f1.equals(f3)); // false
        System.out.println("1.0 ft vs null: " + f1.equals(null)); // false
        System.out.println("Same reference: " + f1.equals(f1));   // true
    }
}