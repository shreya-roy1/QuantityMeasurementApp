package com.quantity.measurement;

/**
 * Enum representing weight units with conversion strategies.
 * Base unit is KILOGRAM.
 */
public enum WeightUnit implements IMeasurable<WeightUnit> {
    GRAM(0.001),
    KILOGRAM(1.0),
    POUND(0.453592); // 1 pound = 0.453592 kg

    private final double factor;

    WeightUnit(double factor) {
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
