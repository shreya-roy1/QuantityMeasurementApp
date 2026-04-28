# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- The QuantityMeasurementApp class is instantiated with the refactored design from UC1–UC7.
- Two numerical values with their respective unit types (feet, inches, yards, centimeters) are provided for comparison, conversion, or arithmetic operations.
- The LengthUnit enum exists as a standalone class with responsibility for unit-specific conversion logic.
- Conversion factors between supported length units are defined as constants within LengthUnit.
- All existing functionality from UC1–UC7 continues to work without modification to client code.

# Main Flow
- Enum Refactoring:
 - Move LengthUnit from inside QuantityLength to a standalone top-level class.
 - Add conversion responsibility to LengthUnit: methods to convert from base unit and to base unit.

- Unit Conversion Logic:
 - Implement convertToBaseUnit(double value) method in LengthUnit to convert a value in this unit to feet (base unit).
 - Implement convertFromBaseUnit(double baseValue) method in LengthUnit to convert a base unit value (feet) to this unit.

- QuantityLength Simplification:
 - Remove internal conversion logic from QuantityLength.
 - Delegate all conversion operations to the unit's conversion methods.
 - QuantityLength now focuses solely on value comparison and arithmetic logic.

- Backward Compatibility:
 - All existing test cases from UC1–UC7 pass without modification.
 -Client code continues to work with the same public API.

- Scalability Pattern:
 - The refactored design establishes a pattern for future measurement categories.
 - New units (WeightUnit, VolumeUnit, TemperatureUnit) can follow the same extraction and responsibility pattern.

# Postconditions
- LengthUnit is now a standalone enum with full responsibility for unit conversions.
- QuantityLength is simplified and focused on value comparison and arithmetic operations.
- Circular dependency risk is eliminated by separating enum and quantity classes.
Single Responsibility Principle is upheld: LengthUnit handles conversions, QuantityLength handles comparisons/arithmetic.
- All equality, conversion, and addition operations from UC1–UC7 work identically.
- The architectural pattern supports straightforward addition of new measurement categories without refactoring existing code.
- Code cohesion is improved; unit-specific logic is centralized in the unit class.