# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- Quantity Length class (from UC3/UC4) and LengthUnit enum exist and include FEET, INCHES, YARDS, CENTIMETERS.
- The conversionFactor for each LengthUnit is defined relative to the chosen base unit (feet or another consistent base).
- Input: numeric value, a valid source LengthUnit, and a valid target LengthUnit.

# Main Flow
- Client calls Quantity Length.convert(value, sourceUnit, targetUnit) or uses an instance method to request conversion.
- The method validates:
 - value is a finite number (Double.isFinite or equivalent).
 - sourceUnit and targetUnit are non-null and members of LengthUnit.

- Convert the input value to the common base unit (e.g., feet) using sourceUnit.getConversionFactor().
- Convert from the base unit to the target unit by dividing by targetUnit.getConversionFactor() (or multiplying by appropriate reciprocal).
- Apply optional rounding or precision handling (caller-specified or a default epsilon).
- Return the converted numeric value to the caller.

# Postconditions
- A numeric value representing the original measurement expressed in the target unit is returned.
- Invalid inputs (null unit, unsupported unit, NaN, infinite) result in a documented exception (e.g., IllegalArgumentException) or a well-defined error response.
- Conversion preserves mathematical equivalence within floating-point precision limits.