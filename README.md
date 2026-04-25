# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

# Preconditions
- The QuantityMeasurementApp class is instantiated.
- Two numerical values in feet and inches are hard-coded for comparison.

# Main Flow
- The main method calls the static method, which validates two numerical values in feet.
- The main method calls the static method, which validates two numerical values in inches.
- These static methods internally instantiate the Feet and Inches class and then call the equality method.
- Both classes validate the input values to ensure they are numeric.
- Both classes compare the two values for equality.
- The result of the comparison is returned to the user.

# Postconditions
- The equality result (true or false) is returned based on the comparison of the converted values.
- Both inch-to-inch and feet-to-inch comparisons are supported.
