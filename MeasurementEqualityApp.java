package com.apps.quantitymeasurement;

public class MeasurementEqualityApp {

    public static class FeetMeasurement {
        private final double value;

        public FeetMeasurement(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            FeetMeasurement other = (FeetMeasurement) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static class InchesMeasurement {
        private final double value;

        public InchesMeasurement(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            InchesMeasurement other = (InchesMeasurement) obj;
            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    public static void checkFeetEquality() {
        FeetMeasurement f1 = new FeetMeasurement(1.0);
        FeetMeasurement f2 = new FeetMeasurement(1.0);
        FeetMeasurement f3 = new FeetMeasurement(2.0);

        System.out.println("Feet Equality:");
        System.out.println("1.0 ft == 1.0 ft → " + f1.equals(f2));
        System.out.println("1.0 ft == 2.0 ft → " + f1.equals(f3));
    }

    public static void checkInchesEquality() {
        InchesMeasurement i1 = new InchesMeasurement(1.0);
        InchesMeasurement i2 = new InchesMeasurement(1.0);
        InchesMeasurement i3 = new InchesMeasurement(2.0);

        System.out.println("\nInches Equality:");
        System.out.println("1.0 in == 1.0 in → " + i1.equals(i2));
        System.out.println("1.0 in == 2.0 in → " + i1.equals(i3));
    }

    public static void main(String[] args) {
        checkFeetEquality();
        checkInchesEquality();
    }
}