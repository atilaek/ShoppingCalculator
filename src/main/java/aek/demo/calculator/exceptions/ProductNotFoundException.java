package aek.demo.calculator.exceptions;

/**
 * Throws a controlled exception when
 * the product requested is not found in the system
 * thrown at @{@link aek.demo.calculator.model}
 * Captured at @{@ShoppingCalculator}
 *
 * @author Atila Ekimci
 */
public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}
}
