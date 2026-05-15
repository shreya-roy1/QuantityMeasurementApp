public class GenericQuantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public GenericQuantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    // --- Subtraction Methods ---

    public GenericQuantity<U> minus(GenericQuantity<U> other) {
        return minus(other, this.unit);
    }

    public GenericQuantity<U> minus(GenericQuantity<U> other, U targetUnit) {
        validateMeasurementCategory(other);
        
        double baseResult = this.unit.convertToBaseUnit(this.value) - 
                           other.unit.convertToBaseUnit(other.value);
        
        double convertedValue = targetUnit.convertFromBaseUnit(baseResult);
        return new GenericQuantity<>(precisionRound(convertedValue), targetUnit);
    }

    // --- Division Methods ---

    public double ratio(GenericQuantity<U> other) {
        validateMeasurementCategory(other);
        if (other.value == 0) throw new ArithmeticException("Division by zero quantity");

        double baseThis = this.unit.convertToBaseUnit(this.value);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        
        return baseThis / baseOther;
    }

    // --- Helpers ---

    private void validateMeasurementCategory(GenericQuantity<U> other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category arithmetic is not allowed");
        }
    }

    private double precisionRound(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "GenericQuantity{" + "value=" + value + ", unit=" + unit + '}';
    }
}