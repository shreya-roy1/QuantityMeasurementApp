# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- The QuantityMeasurementApp class is instantiated.
- Two numerical values with their respective unit types (feet, inches, etc.) are provided for comparison.
- The conversion factors between supported units are defined as constants.

# Main Flow
- User inputs two numerical values with their respective unit types.
- The Quantity Length class validates the input values to ensure they are numeric.
- The Quantity Length class validates the unit type against supported units.
- Both values are converted to a common base unit (e.g., feet) using conversion factors.
- The converted values are compared for equality.
- The result of the comparison is returned to the user.

# Postconditions
- The equality result (true or false) is returned based on the comparison of the converted values.
- All previous functionality from UC1 and UC2 is preserved and works correctly.
- Code duplication is eliminated; maintenance is simplified.