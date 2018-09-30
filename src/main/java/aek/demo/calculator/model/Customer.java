package aek.demo.calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer class that holds data
 * of all details of customer with its purchases
 *
 * @author Atila Ekimci
 */
public class Customer {
    private List<Product> purchasedProducts;
    private List<Promotion> appliedPromotions;
    private List<String> discountDetails;
    private double subtotal;
    private double total;


    public Customer() {
        purchasedProducts = new ArrayList<>();
        appliedPromotions = new ArrayList<>();
        discountDetails = new ArrayList<>();
        subtotal = 0;
        total = 0;

    }

    public void addPurchase(final Product product) {
        subtotal += product.getPrice();
        total += product.getPrice();
        purchasedProducts.add(product);
    }

    /**
     * The method is for adding product as purchase
     * with its discount percentage.
     * It:
     * 1- adds original price to subtotal,
     * 2- sets the discount details for receipt
     * 3- adds discount price to total,
     * 4- adds product to purchased products list.
     *
     * @param product            the amoaunt requested
     * @param discountPercentage the service class holding all
     *                           lenders
     */
    public void addDiscountedPurchase(final Product product, final int discountPercentage) {
        subtotal += product.getPrice();

        double discountPrice = product.getPrice() * (100 - discountPercentage) / 100;
        discountDetails.add(product.getName() + " "
                + discountPercentage + "% off: -" +
                BigDecimal.valueOf(product.getPrice() - discountPrice).setScale(2, RoundingMode.HALF_DOWN) + "p");
        product.setPrice(discountPrice);

        total += discountPrice;

        purchasedProducts.add(product);
    }


    public void addPromotion(final Promotion promotion) {
        appliedPromotions.add(promotion);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getTotal() {
        return total;
    }

    public List<Promotion> getAppliedPromotions() {
        return appliedPromotions;
    }

    public List<String> getDiscountDetails() {
        return discountDetails;
    }
}
