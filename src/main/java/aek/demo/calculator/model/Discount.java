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
public class Discount {

    @SerializedName("percentage")
    @Expose
    private Integer percentage;
    @SerializedName("productids")
    @Expose
    private Set<Integer> productids = null;
    @SerializedName("maxamount")
    @Expose
    private Integer maxamount;

    public Discount() {
    }

    public Discount(Integer percentage, Set<Integer> productids, Integer maxamount) {
        this.percentage = percentage;
        this.productids = productids;
        this.maxamount = maxamount;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public Set<Integer> getProductids() {
        return productids;
    }

    public void setProductids(Set<Integer> productids) {
        this.productids = productids;
    }

    public Integer getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(Integer maxamount) {
        this.maxamount = maxamount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("percentage", percentage).append("productids", productids).append("maxamount", maxamount).toString();
    }
}
