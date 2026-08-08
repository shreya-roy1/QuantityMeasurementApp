package com.quantity.measurement;

/**
 * Enum representing volume units with conversion strategies.
 * Base unit is LITRE.
 */
public enum VolumeUnit implements IMeasurable<VolumeUnit> {
    MILLILITRE(0.001),
    LITRE(1.0),
    GALLON(3.78541); // 1 gallon = 3.78541 litres

    private final double factor;

    VolumeUnit(double factor) {
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
