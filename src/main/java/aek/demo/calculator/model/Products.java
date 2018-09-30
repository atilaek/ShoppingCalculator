package aek.demo.calculator.model;

import aek.demo.calculator.exceptions.ProductNotFoundException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Set;

/**
 * Data POJO based on http://www.jsonschema2pojo.org
 *
 * @author Atila Ekimci
 */
public class Products {

    @SerializedName("products")
    @Expose
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Products() {
    }

    public Products(Set<Product> products) {
        this.products = products;
    }

    /**
     * It gets product based on it's name
     *
     * @param  name         name of product
     *
     * @return Product      found product
     *
     * @throws ProductNotFoundException if the product with name does not exist.
     */
    public Product getProduct(final String name) {
        return products.stream().
                filter(product -> product.getName().equals(name)).
                findFirst().orElseThrow(() -> new ProductNotFoundException("Product with name='" + name + "' is not found!"));
    }

    /**
     * It gets product based on it's id
     *
     * @param  id           id number of product
     *
     * @return Product      found product
     *
     * @throws ProductNotFoundException if the product with name does not exist.
     */

    public Product getProduct(final int id) {
        return products.stream().
                filter(product -> product.getId() == id).
                findFirst().orElseThrow(() -> new ProductNotFoundException("Product with id='" + id + "' is not found!"));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("products", products).toString();
    }

}
