# Refactoring Notes :
1. `Calculator.java` 
- **Rename method** `calc` → `sumOverProduct` (more descriptive)
- **Rename method** `prtRes` → `printResult` (clearer name)
2. `OrderProcessor.java`
- **Extract constant** for member discount: `0.9` → `private static final double MEMBER_DISCOUNT_RATE = 0.9;`
- **Extract method** for calculating total price (to avoid code duplication between discount application and printing)
- **Extract method** for printing a single item line
3. `Order`, `Customer`, `Item` **classes**
- No changes needed – they are simple data holders and already use conventional names.