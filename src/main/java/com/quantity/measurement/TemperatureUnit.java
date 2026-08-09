package com.quantity.measurement;

/**
 * Enum representing temperature units with conversion strategies.
 * Handles non-linear offset conversions.
 * Base unit is CELSIUS.
 */
public enum TemperatureUnit implements IMeasurable<TemperatureUnit> {
    CELSIUS {
        @Override
        public double convertToBaseUnit(double value) {
            return value;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue;
        }
    },
    FAHRENHEIT {
        @Override
        public double convertToBaseUnit(double value) {
            return (value - 32.0) * 5.0 / 9.0;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue * 9.0 / 5.0 + 32.0;
        }
    },
    KELVIN {
        @Override
        public double convertToBaseUnit(double value) {
            return value - 273.15;
        }

        @Override
        public double convertFromBaseUnit(double baseValue) {
            return baseValue + 273.15;
        }
    }
}
