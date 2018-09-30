package aek.demo.calculator.model;

import aek.demo.calculator.exceptions.NotEnoughProductException;
import aek.demo.calculator.service.repository.CVSFileReader;

import java.util.Set;

/**
 * Store class that holds data about all products and promotions on them
 *
 * @author Atila Ekimci
 */
public class Store {
    private Products storeProducts;
    private Set<Promotion> promotions;

    public Store() {
        storeProducts = CVSFileReader.readProductsJsonFile();
        promotions = CVSFileReader.readPromotionsJsonFile();
    }

    public Store(Products storeProducts, Set<Promotion> promotions) {
        this.storeProducts = storeProducts;
        this.promotions = promotions;
    }

    /**
     * this method finds and sends one product based on given name.
     * Also updates the amount from store count as -1.
     * Other product variables are filled from store product itself.
     *
     * @param name          name of the product

     * @return Product      product created
     *
     * @throws NotEnoughProductException  if the insufficient amount left od that product in the store.
     */
    public Product takeAProduct(final String name) {
        Product storeProduct = storeProducts.getProduct(name);
        if (storeProduct.getAmount() == 0) throw new NotEnoughProductException("There's not any " + name + "s left");

        storeProduct.setAmount(storeProduct.getAmount() - 1);

        return new Product(storeProduct.getId(),
                storeProduct.getName(),
                storeProduct.getPrice(),
                storeProduct.getAmount() + 1);
    }

    /**
     * Creates a single duplicate product object
     * with given parameters.
     * Other product variables are filled from store product.
     *
     * @param id       the id of product

     * @return Product      created product
     */
    public Product getSingleDuplicateStoreProduct(final int id) {
        Product storeProduct = storeProducts.getProduct(id);
        return new Product(storeProduct.getId(),
                storeProduct.getName(),
                storeProduct.getPrice(),
                storeProduct.getAmount());
    }

    /**
     * Creates a single duplicate product object
     * with given parameters.
     * Other product variables are filled from store product.
     *
     * @param id       the id of product
     * @param amount   amount of product

     * @return Product      created product
     */
    public Product getSingleDuplicateStoreProduct(final int id, final int amount) {
        Product storeProduct = storeProducts.getProduct(id);
        return new Product(storeProduct.getId(),
                storeProduct.getName(),
                storeProduct.getPrice(),
                amount);
    }

    public Set<Promotion> getPromotions() {
        return promotions;
    }

}
