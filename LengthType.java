public enum LengthType implements UnitConverter {

    FEET(0.3048, "Feet"),
    INCH(0.0254, "Inch"),
    YARD(0.9144, "Yard"),
    CENTIMETER(0.01, "Centimeter");

    private final double conversionFactor;
    private final String unitName;

    LengthType(
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