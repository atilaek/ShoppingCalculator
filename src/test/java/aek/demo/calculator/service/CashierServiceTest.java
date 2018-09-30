package aek.demo.calculator.service;

import aek.demo.calculator.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Test av {@link CashierService}
 *
 * @author Atila Ekimci
 */
public class CashierServiceTest {


    Store store;
    ShoppingCart shoppingCart;
    Customer customer;
    CashierService cashierService;

    @Before
    public void setup() {
        store = new Store(createStoreProducts(), createPromotions());
        shoppingCart = new ShoppingCart();
        shoppingCart.addToBasket(new Product(1, "Soup", 0.65, 1));
        shoppingCart.addToBasket(new Product(1, "Soup", 0.65, 1));
        shoppingCart.addToBasket(new Product(2, "Bread", 0.80, 1));
        shoppingCart.addToBasket(new Product(4, "Apple", 1.00, 1));
        customer = new Customer();
        cashierService = new CashierService(shoppingCart, store, customer);

    }

    @Test
    public void purchaseWithPromotions() {
        cashierService.purchaseWithPromotions();
        assertEquals(2, customer.getAppliedPromotions().size());
    }

    private Set<Promotion> createPromotions() {
        Set<Discount> discounts1 = Stream.of(new Discount(10, Stream.of(4).collect(Collectors.toSet()), 5)).collect(Collectors.toSet());
        Promotion promotion1 = new Promotion("Apple 10%", null, discounts1);


        Set<Product> products2 = new HashSet<>(Arrays.asList(new Product(1, "", 0, 2)));
        Set<Discount> discounts2 = Stream.of(new Discount(50, Stream.of(2).collect(Collectors.toSet()), 1)).collect(Collectors.toSet());
        Promotion promotion2 = new Promotion("Buy 2 tins of soup and get a loaf of bread for half price"
                , products2, discounts2);
        return Stream.of(promotion1, promotion2).collect(Collectors.toSet());
    }

    private Products createStoreProducts() {
        return new Products(new HashSet<>(Arrays.asList(new Product(1, "Soup", 0.65, 50),
                new Product(2, "Bread", 0.80, 50),
                new Product(3, "Milk", 1.30, 50),
                new Product(4, "Apple", 1.00, 50))));
    }

}
