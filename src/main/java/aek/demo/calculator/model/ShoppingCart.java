package aek.demo.calculator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ShoppingCart class that holds data of products planned to be purchased
 *
 * @author Atila Ekimci
 */
public class ShoppingCart {
    private Map<Integer, Product> basket;

    public ShoppingCart() {
        basket = new HashMap<>();
    }

    public Map<Integer, Product> getBasket() {
        return basket;
    }


    /**
     * check if product exists in the basket
     * by looking at it's id
     *
     * @param product       Product object
     *
     * @return boolean      true if product exists
     */
    private boolean hasProductInTheBasket(final Product product) {
        if (basket.containsKey(product.getId())) {
            return true;
        }
        return false;
    }

    /**
     * check if product exists in the basket
     * based on it's id
     *
     * @param id             id of product
     *
     * @return boolean       true if product exists
     */
    public boolean hasProductInTheBasket(final int id) {
        if (basket.containsKey(id)) {
            return true;
        }
        return false;
    }

    /**
     * Adds product to the basket.
     * Checks first if that product type exists in the basket
     *
     * @param product       Product object
     */
    public void addToBasket(Product product) {
        int currentAmount = hasProductInTheBasket(product) ?
                basket.get(product.getId()).getAmount() :
                0;

        basket.put(product.getId(),
                new Product(product.getId(), product.getName(), product.getPrice(), currentAmount + 1));
    }


    /**
     * The method is for checking if basket contains
     * all the required products with their minimum amounts.
     * If there's no purchase-required product then skips.
     *
     * @param requiredProducts          required products that needs to exist
     *                                  in the basket with those minimum amounts.
     *
     * @return hasRequiredProducts      true if required products with minimum amounts
     *                                  exist in the basket.
     */
    public boolean hasAllRequiredProductsWithMinAmountsInTheBasket(final Set<Product> requiredProducts) {
        final boolean[] hasRequiredProducts = {true};
        if (requiredProducts != null) {
            requiredProducts.forEach(requiredProduct -> {
                if (!hasProductWithMinAmountInTheBasket(requiredProduct.getId(), requiredProduct.getAmount()))
                    hasRequiredProducts[0] = false;
            });
        }
        return hasRequiredProducts[0];
    }

    /**
     * The method is for checking if basket contains
     * any of the discount products.
     *
     * @param discounts             discounts that has list of product ids.
     *
     * @return hasDiscountProduct   true if any discount product exist
     *                              in the basket.
     */
    public boolean hasAnyDiscountProductInTheBasket(final Set<Discount> discounts) {
        final boolean[] hasDiscountProduct = {true};
        discounts.forEach(discount -> {
            discount.getProductids().forEach(productId -> {
                if (!hasProductInTheBasket(productId)) {
                    hasDiscountProduct[0] = false;
                }
            });
        });
        return hasDiscountProduct[0];
    }

    /**
     * The method is for checking if basket contains
     * the product with given minimum amount.
     *
     * @param productId productid of product.
     * @param amount    minimum amount of product.
     * @return boolean  true if product exists in the basket
     *                  minimum with that amount
     */
    public boolean hasProductWithMinAmountInTheBasket(final int productId, final int amount) {
        if (hasProductInTheBasket(productId)) {
            if (basket.get(productId).getAmount() >= amount) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes product from the basket.
     * Checks first if that product type exists in the basket.
     * If there's not requested amount of products,
     * then removes and return the existing amount in the basket.
     *
     * @param product   Product object to be removed from basket
     *
     * @return int      Amount removed from basket.
     */
    public int removeFromBasket(final Product product) {
        int currentAmount = hasProductInTheBasket(product.getId()) ?
                basket.get(product.getId()).getAmount() :
                0;
        if (currentAmount - product.getAmount() <= 0) {
            basket.remove(product.getId());
            return currentAmount;
        } else {
            basket.replace(product.getId(),
                    new Product(product.getId(), product.getName(), product.getPrice(), currentAmount - product.getAmount()));
            return currentAmount - product.getAmount();
        }
    }
}
