public enum LengthUnit implements IMeasurable {
    FEET(12.0), INCHES(1.0);

    private final double factor;
    LengthUnit(double factor) { this.factor = factor; }

    @Override
    public double convertToBaseUnit(double val) { return val * factor; }
    
    @Override
    public double convertFromBaseUnit(double base) { return base / factor; }
    
    @Override
    public String getUnitName() { return this.name(); }
}