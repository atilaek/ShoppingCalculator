package aek.demo.calculator.exceptions;

/**
 * Throws a controlled exception when
 * there's insufficient amount of product in the store
 * thrown at @{@link aek.demo.calculator.model}
 * Captured at @{@ShoppingCalculator}
 *
 * @author Atila Ekimci
 */
public class NotEnoughProductException extends RuntimeException {

	public NotEnoughProductException(String message) {
		super(message);
	}
}
