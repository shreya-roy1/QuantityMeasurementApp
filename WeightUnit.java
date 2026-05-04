public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double conversionFactor; // relative to kg

    WeightUnit(double factor) {
        this.conversionFactor = factor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert given value to base unit (kg)
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    // Convert from base unit (kg) to this unit
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}