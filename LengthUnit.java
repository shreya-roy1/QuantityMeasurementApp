package com.apps.quantitymeasurement;

public enum LengthUnit {

    FEET(1.0),
    INCH(1.0 / 12.0),
    YARD(3.0),
    CENTIMETER(0.0328084);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // ✅ Convert ANY unit → base (feet)
    public double toBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // ✅ Convert base (feet) → THIS unit
    public double fromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public double getConversionFactor() {
        return toFeetFactor;
    }
}