package aek.demo.calculator.service.repository;

import aek.demo.calculator.model.Products;
import aek.demo.calculator.model.Promotion;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * CVS file reading class for json that has static file reading function
 * used by {@link aek.demo.calculator.model.Store}
 *
 * @author Atila Ekimci
 */
public class CVSFileReader {

    private static final String productsFilePrefix = "C://products.json";
    private static final String promotionsPrefix = "C://promotions.json";

    /**
     * Reads CSV file through the file path and put them in a @{@link Products}
     *
     * @return @{@link Products}    Set of products in the store
     * @throws IOException          For  I/O exception occurred.
     */
    public static Products readProductsJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        Products products = null;
        try {
            File file = new File(productsFilePrefix);
            // Convert JSON string from file to Object
            products = mapper.readValue(file, Products.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    /**
     * Reads CSV file through the file path and put them in a @{@link Set<Promotion>}
     *
     * @return @{@link Products}    Set of promotions for the store.
     * @throws IOException          For  I/O exception occurred.
     */
    public static Set<Promotion> readPromotionsJsonFile() {
        ObjectMapper mapper = new ObjectMapper();
        Set<Promotion> promotion = null;
        try {
            File file = new File(promotionsPrefix);
            // Convert JSON string from file to Object
            promotion = mapper.readValue(file, new TypeReference<Set<Promotion>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return promotion;
    }
}
