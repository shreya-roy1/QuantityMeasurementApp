# Quantity Measurement App - Design Notes

This document captures the design notes, architectural choices, and core considerations for the Quantity Measurement Application.

## Project Goal
To design and implement a flexible, type-safe, and extensible measurement conversion and arithmetic engine.

## Core Design Goals
1. **Compile-time safety**: Prevent invalid comparisons/arithmetic across different physical dimensions.
2. **Extensibility**: Make it easy to add new unit categories (e.g., Temperature) without editing existing code.
3. **Accuracy**: Ensure high-precision floating point arithmetic and comparison limits.

## 📏 Length Conversion Engine
For the Length category, we require conversion and comparison between Feet, Inches, Yards, and Centimeters.
- **Base Unit**: `INCH` (1.0)
- **Conversion Factors**:
  - `FEET` = 12.0 inches
  - `YARD` = 36.0 inches
  - `CENTIMETER` = 0.4 inches (approximate mapping)

All length comparison values are normalized to the base unit `INCH` before comparison.
