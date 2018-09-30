package aek.demo.calculator.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Set;

/**
 * Data POJO based on http://www.jsonschema2pojo.org
 *
 * @author Atila Ekimci
 */
public class Promotion {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("products")
    @Expose
    private Set<Product> products = null;
    @SerializedName("discount")
    @Expose
    private Set<Discount> discount = null;

    public Promotion() {
    }

    public Promotion(final String title, final Set<Product> products, final Set<Discount> discount) {
        this.title = title;
        this.products = products;
        this.discount = discount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Discount> getDiscount() {
        return discount;
    }

    public void setDiscount(Set<Discount> discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("products", products).append("discount", discount).toString();
    }

}
