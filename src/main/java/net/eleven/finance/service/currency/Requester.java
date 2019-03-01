package net.eleven.finance.service.currency;

import java.io.IOException;

/**
 * Created by eleven on 02.12.2018.
 */
public interface Requester {
    String makeRequest(String path) throws IOException;
}
