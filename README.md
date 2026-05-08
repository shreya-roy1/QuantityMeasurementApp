# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused.  

# Preconditions
- The refactored Quantity<U extends IMeasurable> class from UC10 is fully operational.
- The IMeasurable interface is defined with methods for unit conversions.
- Both LengthUnit and WeightUnit enums implement IMeasurable and are fully functional.
- All functionality from UC1–UC10 is preserved and unaffected by UC11 additions.
- A new VolumeUnit enum will be created implementing IMeasurable with LITRE as the base unit.
- Conversion factors for all volume units are defined relative to litres (base unit).
- Volume measurements are treated as a separate, non-interoperable category from length and weight.
- No modifications to existing Quantity<U>, IMeasurable, or QuantityMeasurementApp are required.


# Main Flow
- Create VolumeUnit Enum Implementing IMeasurable
- Define an enum with volume units (LITRE, MILLILITRE, GALLON).
- Assign conversion factors relative to the base unit (litre)
- Implement all IMeasurable interface methods:

# Equality Comparison
- User inputs two numerical values with their respective volume unit types.
- Quantity<VolumeUnit> class (inherited from generic Quantity<U>) validates input values.
- Both values are converted to the common base unit (litre) using VolumeUnit conversion methods.
- The converted values are compared for equality using the generic equals() method.
- The result of the comparison (true or false) is returned.

# Unit Conversion
- User inputs a numerical value, source unit, and target unit (all volume units).
- Quantity<VolumeUnit>.convertTo(targetUnit) converts the measurement to the target unit.
- The method normalizes through the base unit (litre) and applies appropriate conversion factors.
- A new Quantity<VolumeUnit> object is returned with the converted value and target unit.

# Addition Operations
- User inputs two Quantity<VolumeUnit> objects and optionally a target unit.
- Both measurements are converted to the base unit (litre) using the generic add() method.
- The converted values are summed.
- The result is converted to the target unit (either first operand's unit or explicitly specified unit).
- A new Quantity<VolumeUnit> object representing the sum is returned.

# Cross-Category Type Safety
- Attempting to compare volume with length or weight returns false (different categories).
- Compiler prevents mixing Quantity<VolumeUnit> with Quantity<LengthUnit> or Quantity<WeightUnit>.
- Runtime type checking in equals() method ensures category isolation.

# Integration with Existing System
- VolumeUnit enum is used seamlessly with the existing generic Quantity<U> class.
- No modifications to QuantityMeasurementApp needed; existing generic methods handle volume quantities.
- All existing demonstration and test methods work with volume units automatically.


# Postconditions
- Volume measurements of the same unit and value are considered equal.
- Volume measurements of different units but equivalent values are considered equal (e.g., 1 L = 1000 mL = ~0.264172 gallons).
- Unit conversions between volume units produce mathematically accurate results within floating-point precision.
- Addition of two volume measurements produces a new Quantity<VolumeUnit> object without modifying originals (immutability).
- All previous functionality from UC1–UC10 for length and weight measurements is preserved and works correctly.
- Volume, length, and weight measurements are treated as separate, non-interoperable categories.
- No modifications to Quantity<U>, IMeasurable, QuantityMeasurementApp, or existing test infrastructure are required.
- The architectural pattern is validated as truly scalable; new categories integrate effortlessly.
- Adding additional measurement categories (temperature, time, etc.) follows the identical pattern.
