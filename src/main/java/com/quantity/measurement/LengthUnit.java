package com.quantity.measurement;

/**
 * Enum representing length units with conversion strategies.
 * Base unit is INCHES.
 */
public enum LengthUnit implements IMeasurable<LengthUnit> {
    INCHES(1.0),
    FEET(12.0),
    YARD(36.0),
    CENTIMETER(1.0 / 2.54); // 1 inch = 2.54 cm

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}
