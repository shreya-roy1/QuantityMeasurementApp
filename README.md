# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- The QuantityMeasurementApp class is instantiated with the refactored QuantityLength class from UC3.
- Two numerical values, with their respective units (feet, inches, yards), are provided for comparison.
- The conversion factor for yards (1 yard = 3 feet) is defined in the LengthUnit enum.
- The conversion factor for yards (1 cm = 0.393701 in) is defined in the LengthUnit enum.

# Main Flow
- Users input two numerical values with their respective unit types (feet, inches, yards or cms).
- The QuantityLength class validates the input values to ensure they are numeric.
- The QuantityLength class validates the unit type against supported units (feet, inches, yards, cms).
- Both values are converted to a common base unit (in or feet) using conversion factors.
- The converted values are compared for equality.
- The result of the comparison is returned to the user.

# Postconditions
- The equality result (true or false) is returned based on the comparison of the converted values.
- All previous functionality from UC1, UC2, and UC3 is preserved and works correctly.
- Yard-to-yard, yard-to-feet, and yard-to-inches comparisons are fully supported.
- Similarly, all comparisons with respect to centimeters should be supported (cm-to-cm, cm-to-inch, cm-to-feet, etc.)
- Code remains free of duplication; adding new units requires only enum modification.