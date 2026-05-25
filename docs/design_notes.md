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

## ⚖️ Weight Conversion Engine
For the Weight category, we need support for Kilograms, Grams, and Pounds.
- **Base Unit**: `KILOGRAM` (1.0)
- **Conversion Factors**:
  - `KILOGRAM` = 1.0 kg
  - `GRAM` = 0.001 kg
  - `POUND` = 0.453592 kg

All weight calculations and equality comparisons normalize to `KILOGRAM` first.

## 🧪 Volume Conversion Engine
For the Volume category, we support Litres, Millilitres, and Gallons.
- **Base Unit**: `LITRE` (1.0)
- **Conversion Factors**:
  - `LITRE` = 1.0 L
  - `MILLILITRE` = 0.001 L
  - `GALLON` = 3.78541 L

All volume operations resolve to `LITRE` internally.
