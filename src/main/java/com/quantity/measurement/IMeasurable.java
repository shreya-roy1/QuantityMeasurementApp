package com.quantity.measurement;

/**
 * Interface representing a measurable unit that can be converted to and from a base unit.
 *
 * @param <U> The specific unit type
 */
public interface IMeasurable<U> {
    /**
     * Converts a value in this unit to the base unit of its measurement category.
     *
     * @param value the value in this unit
     * @return the value converted to the base unit
     */
    double convertToBaseUnit(double value);

    /**
     * Converts a base unit value to this unit.
     *
     * @param baseValue the value in the base unit
     * @return the value converted to this unit
     */
    double convertFromBaseUnit(double baseValue);
}
