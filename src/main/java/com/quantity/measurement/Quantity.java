package com.quantity.measurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/**
 * Generic Quantity class handling measurements across convertible unit categories.
 * Enforces DRY using a centralized validation and arithmetic helper.
 *
 * @param <U> The enum type representing the unit, implementing IMeasurable
 */
public class Quantity<U extends Enum<U> & IMeasurable<U>> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Quantity value must be a finite number.");
        }
        this.value = roundToTwoDecimals(value);
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // =========================================================================
    // Arithmetic Operation Enum
    // =========================================================================

    /**
     * Enum encapsulating arithmetic operations on base double values.
     * Uses DoubleBinaryOperator functional interface for concise lambda execution.
     */
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) {
                throw new ArithmeticException("Division by zero is not allowed.");
            }
            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(DoubleBinaryOperator operator) {
            this.operator = operator;
        }

        public double compute(double baseValue1, double baseValue2) {
            return operator.applyAsDouble(baseValue1, baseValue2);
        }
    }

    // =========================================================================
    // Centralized Helpers
    // =========================================================================

    /**
     * Centralized validation helper for arithmetic operands and target units.
     */
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired) {
        if (other == null) {
            throw new IllegalArgumentException("Operand quantity cannot be null.");
        }
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        if (!this.unit.getDeclaringClass().equals(other.unit.getDeclaringClass())) {
            throw new IllegalArgumentException("Cannot perform arithmetic across different measurement categories.");
        }
        if (targetUnitRequired && !this.unit.getDeclaringClass().equals(targetUnit.getDeclaringClass())) {
            throw new IllegalArgumentException("Target unit belongs to a different measurement category.");
        }
        if (!Double.isFinite(this.value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Operands must have finite numeric values.");
        }
    }

    /**
     * Core arithmetic helper converting both quantities to base unit,
     * executing operation computation, and returning the base-unit result.
     */
    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);
        return operation.compute(thisBase, otherBase);
    }

    // =========================================================================
    // Public Arithmetic Methods
    // =========================================================================

    /** Addition with implicit target unit (this quantity's unit) */
    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    /** Addition with explicit target unit */
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double targetResult = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(targetResult, targetUnit);
    }

    /** Subtraction with implicit target unit (this quantity's unit) */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    /** Subtraction with explicit target unit */
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateArithmeticOperands(other, targetUnit, true);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double targetResult = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(targetResult, targetUnit);
    }

    /**
     * Division producing a dimensionless scalar quotient.
     */
    public double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    /**
     * Converts this quantity to a different unit of the same category.
     *
     * @param targetUnit the target unit to convert to
     * @return a new Quantity in the target unit
     */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        if (!this.unit.getDeclaringClass().equals(targetUnit.getDeclaringClass())) {
            throw new IllegalArgumentException("Target unit belongs to a different measurement category.");
        }
        double baseResult = this.unit.convertToBaseUnit(this.value);
        double targetResult = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(targetResult, targetUnit);
    }

    // =========================================================================
    // Utility Methods & Overrides
    // =========================================================================

    private static double roundToTwoDecimals(double val) {
        return Math.round(val * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?> other)) return false;
        if (!this.unit.getDeclaringClass().equals(other.unit.getDeclaringClass())) return false;

        double thisBase = this.unit.convertToBaseUnit(this.value);
        double otherBase = ((IMeasurable<?>) other.unit).convertToBaseUnit(other.value);
        return Math.abs(thisBase - otherBase) < 0.01;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getDeclaringClass(), unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return String.format("Quantity(%.2f, %s)", value, unit);
    }
}
