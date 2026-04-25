# QuantityMeasurementApp
The Quantity Measurement App compares two quantities (length, weight, etc.) and provides a comparison between different units. It progresses further to add Conversion from one unit to another, and also will go on to support quantity arithmetic. The app will be developed incrementally, starting with simple use cases and progressively adding complexity to develop a full-stack app. Each use case defines a specific scope—avoid expanding beyond requirements to keep the application maintainable and focused. 

---

## 🚀 Use Cases

### ✅ UC1: Feet Equality
- Compares two values in **feet**
- Uses `equals()` for comparison  

Example:  
`1.0 ft == 1.0 ft → true`

---

### ✅ UC2: Feet & Inches Equality
- Adds support for **inches**
- Feet and Inches are handled **separately**

Example:  
`1.0 ft == 1.0 ft → true`  
`1.0 in == 1.0 in → true`

---

### ✅ UC3: Quantity Length Equality (Optimized)
- Uses a **single class + enum**
- Removes duplicate code (DRY principle)
- Converts values to a **common unit (feet)**
- Supports cross-unit comparison  

Example:  
`1 ft == 12 in → true`

---

## 📚 Key Learnings
- Proper implementation of equals() method
- Handling floating-point precision
- Writing clean, maintainable code
- Refactoring using DRY principle
- Designing scalable systems using enums and abstraction
 
---

## 💡 Key Idea
The project shows improvement from:
- Basic comparison → Multiple units → Clean and reusable design

---
