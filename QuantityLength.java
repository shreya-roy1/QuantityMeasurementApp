package com.apps.quantitymeasurement;

public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public UC8_QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    private double toBase() {
        return unit.toBaseUnit(value);
    }

    // ✅ Conversion (UC5)
    public UC8_QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = this.toBase();
        double converted = targetUnit.fromBaseUnit(base);

        return new UC8_QuantityLength(converted, targetUnit);
    }

    // ✅ Addition with explicit target (UC7)
    public static UC8_QuantityLength add(UC8_QuantityLength a,
                                         UC8_QuantityLength b,
                                         LengthUnit targetUnit) {

        if (a == null || b == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sum = a.toBase() + b.toBase();
        double result = targetUnit.fromBaseUnit(sum);

        return new UC8_QuantityLength(result, targetUnit);
    }

    // ✅ UC6 (backward compatibility)
    public UC8_QuantityLength add(UC8_QuantityLength other) {
        return add(this, other, this.unit);
    }

    // ✅ Equality (UC3)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UC8_QuantityLength other = (UC8_QuantityLength) obj;
        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return String.format("%.4f %s", value, unit);
    }
}