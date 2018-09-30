package aek.demo.calculator.service;

import aek.demo.calculator.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * CashierService class that calculates purchases with and without promotions
 *
 * @author Atila Ekimci
 */
public class CashierService {

    Store store;
    ShoppingCart shoppingCart;
    Customer customer;

    public CashierService(ShoppingCart shoppingCart, Store store, Customer customer) {
        this.shoppingCart = shoppingCart;
        this.store = store;
        this.customer = customer;
    }

    /**
     * This method represents purchase as transferring product
     * from shopping cart to customer's purchased products list.
     */
    public void purchase() {
        Set<Product> products = new HashSet<>();
        shoppingCart.getBasket().forEach((id, product) -> {
            Product storeProduct = store.getSingleDuplicateStoreProduct(id);
            products.add(storeProduct);
            customer.addPurchase(storeProduct);
        });
    }

    /**
     * The method is for purchasing products listed in @shoppingCart.
     * it first checks discounts with product-purchasing conditions and purchases through them
     */
    public void purchaseWithPromotions() {
        store.getPromotions().stream().filter(promotion -> promotion.getProducts() == null).collect(Collectors.toList());
        getConditionalDiscounts().forEach(promotion ->
                purchaseWithDiscounts(promotion)
        );

        getUnconditionalDiscounts().forEach(promotion ->
                purchaseWithDiscounts(promotion)
        );
    }

    /**
     * The method gets discounts with product-purchase condition
     *
     * @return List<Promotion>  List of Promotions
     */
    private List<Promotion> getConditionalDiscounts() {
        return store.getPromotions().stream().filter(promotion -> promotion.getProducts() != null).collect(Collectors.toList());
    }

    /**
     * The method gets unconditional discounts
     *
     * @return List<Promotion>  List of Promotions
     */
    private List<Promotion> getUnconditionalDiscounts() {
        return store.getPromotions().stream().filter(promotion -> promotion.getProducts() == null).collect(Collectors.toList());
    }

    /**
     * It checks if shopping cart meets product-condition products when required.
     * Also checks if any discount product exists in the shopping basket
     * and then processes the purchase.
     *
     * @param promotion the promotion to be evaluated
     */
    private void purchaseWithDiscounts(Promotion promotion) {
        while (shoppingCart.hasAllRequiredProductsWithMinAmountsInTheBasket(promotion.getProducts()) &&
                shoppingCart.hasAnyDiscountProductInTheBasket(promotion.getDiscount())) {
            if (promotion.getProducts() != null) {
                promotion.getProducts().forEach(requiredProduct -> {
                    Product purchaseProduct = store.getSingleDuplicateStoreProduct(requiredProduct.getId(), requiredProduct.getAmount());
                    shoppingCart.removeFromBasket(purchaseProduct);
                    customer.addPurchase(purchaseProduct);
                });
            }
            promotion.getDiscount().forEach(discount -> {
                discount.getProductids().forEach(discountProductId -> {
                    int takenAmount = shoppingCart.removeFromBasket(store.getSingleDuplicateStoreProduct(discountProductId, discount.getMaxamount()));
                    customer.addDiscountedPurchase(store.getSingleDuplicateStoreProduct(discountProductId, takenAmount), discount.getPercentage());
                });
            });
            customer.addPromotion(promotion);
        }
    }

}
