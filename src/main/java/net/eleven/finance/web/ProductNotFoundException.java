package net.eleven.finance.web;

/**
 * Created by eleven on 19.11.2018.
 */
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long id) {
        super("Could not find currency " + id);
    }
}
