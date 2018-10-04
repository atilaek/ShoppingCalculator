import aek.demo.calculator.model.Customer;
import aek.demo.calculator.model.ShoppingCart;
import aek.demo.calculator.model.Store;
import aek.demo.calculator.service.CashierService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

/**
 * Main Class that takes two command line inputs
 * 1st input as csv file holding market data that has offers for loans as list
 * 2nd input as the amoun borrower wants to loan
 *
 * @author Atila Ekimci
 */
public class ShoppingCalculator {
    public static void main(String[] args) {
        /*Store store = new Store();
        Customer customer = new Customer();
        ShoppingCart shoppingCart = new ShoppingCart();

        for (String arg : args) {
            shoppingCart.addToBasket(store.takeAProduct(arg));
        }
        */
        //For testing via compiler
        Store store = new Store();
        Customer customer = new Customer();
        ShoppingCart shoppingCart = new ShoppingCart();
        /*shoppingCart.addToBasket(store.takeAProduct("Apple"));
        shoppingCart.addToBasket(store.takeAProduct("Apple"));*/
        shoppingCart.addToBasket(store.takeAProduct("Soup"));
        shoppingCart.addToBasket(store.takeAProduct("Soup"));
        shoppingCart.addToBasket(store.takeAProduct("Soup"));
        shoppingCart.addToBasket(store.takeAProduct("Soup"));
        shoppingCart.addToBasket(store.takeAProduct("Bread"));
        shoppingCart.addToBasket(store.takeAProduct("Bread"));



        CashierService cashierService = new CashierService(shoppingCart, store, customer);
        cashierService.purchaseWithPromotions();
        cashierService.purchase();

        System.out.println("Subtotal: £" + BigDecimal.valueOf(customer.getSubtotal())
                .setScale(2, RoundingMode.HALF_UP));

        if (customer.getAppliedPromotions().size() == 0) {
            System.out.println("(no offers available)");
        } else {
            customer.getDiscountDetails().sort(Comparator.naturalOrder());
            customer.getDiscountDetails().forEach(System.out::println);
        }

        System.out.println("Total: £" + BigDecimal.valueOf(customer.getTotal())
                .setScale(2, RoundingMode.HALF_DOWN));


    }
}
