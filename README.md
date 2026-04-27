# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- Quantity Length class (from UC3/UC4/UC5) and LengthUnit enum exist with FEET, INCHES, YARDS, CENTIMETERS.
- The conversionFactor for each LengthUnit is defined relative to a consistent base unit.
- Two Quantity Length objects or raw values with their respective units are provided.
- A target unit is the unit of the first operand.
- All units belong to the same measurement category (length).

# Main Flow
- Client calls Quantity Length.add(length1, length2, targetUnit) or uses an instance method to add two length measurements.
- The method validates:
 - Both length1 and length2 are non-null and have valid LengthUnits.
 - All values are finite numbers (Double.isFinite or equivalent).

- Convert both length1 and length2 to a common base unit (feet).
- Add the converted values.
- Convert the sum from the base unit to the unit of the first operand.
- Return a new Quantity Length object (or numeric value) representing the result in the unit of first operand.

# Postconditions
- A new Quantity Length object is returned with the sum of the two measurements expressed in the unit of the first operand.
- The original Quantity Length objects remain unchanged (immutability principle).
- Invalid inputs (null units, unsupported units, NaN, infinite) result in a documented exception (e.g., IllegalArgumentException).
- Addition is mathematically accurate within floating-point precision limits.
- Addition is commutative: add(A, B) equals add(B, A).
