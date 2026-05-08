import java.util.Objects;

public class Measurement<U extends UnitConverter> {

    private final double value;
    private final U unit;

    public Measurement(double value, U unit) {

        if (value < 0) {
            throw new IllegalArgumentException(
                    "Value cannot be negative"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public Measurement<U> convertTo(U targetUnit) {

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Measurement<>(
                convertedValue,
                targetUnit
        );
    }

    public Measurement<U> add(Measurement<U> other) {

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double totalBase = thisBase + otherBase;

        double finalValue =
                unit.convertFromBaseUnit(totalBase);

        return new Measurement<>(finalValue, unit);
    }

    public Measurement<U> add(
            Measurement<U> other,
            U targetUnit
    ) {

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        double totalBase = thisBase + otherBase;

        double finalValue =
                targetUnit.convertFromBaseUnit(totalBase);

        return new Measurement<>(
                finalValue,
                targetUnit
        );
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Measurement<?> other)) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(this.value);

        double otherBase =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase)
                < 0.0001;
    }

    @Override
    public int hashCode() {

        return Objects.hash(
                unit.convertToBaseUnit(value)
        );
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}