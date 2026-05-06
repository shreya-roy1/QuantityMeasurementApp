import java.util.Objects;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit = unit;
    }

    // Converts the current value to the base unit (e.g., Inches or Grams)
    private double getBaseValue() {
        return unit.convertToBaseUnit(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Quantity<?> that = (Quantity<?>) o;

        // CRITICAL: Prevents comparing 1 Feet to 1 Kilogram at runtime
        if (this.unit.getClass() != that.unit.getClass()) {
            return false;
        }

        return Double.compare(this.getBaseValue(), that.getBaseValue()) == 0;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = this.getBaseValue();
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        // Rounding to 2 decimal places as per UC10 requirements
        double roundedValue = Math.round(convertedValue * 100.0) / 100.0;
        return new Quantity<>(roundedValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double totalBaseValue = this.getBaseValue() + other.getBaseValue();
        double resultValue = targetUnit.convertFromBaseUnit(totalBaseValue);
        return new Quantity<>(resultValue, targetUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBaseValue(), unit.getClass());
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.1f, %s)", value, unit.getUnitName());
    }
}