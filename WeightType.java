public enum WeightType implements UnitConverter {

    KILOGRAM(1.0, "Kilogram"),
    GRAM(0.001, "Gram"),
    POUND(0.453592, "Pound");

    private final double conversionFactor;
    private final String unitName;

    WeightType(
            double conversionFactor,
            String unitName
    ) {
        this.conversionFactor = conversionFactor;
        this.unitName = unitName;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return unitName;
    }
}