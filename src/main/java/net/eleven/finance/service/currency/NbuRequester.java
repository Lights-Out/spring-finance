package net.eleven.finance.service.currency;

import net.eleven.finance.model.RateProvider;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by eleven on 02.12.2018.
 */
@Component
@RequesterType(provider = RateProvider.NBU)
public class NbuRequester implements Requester {
    private static final String REQUEST_URI = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    public String makeRequest(String path) throws IOException {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(REQUEST_URI + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            byte[] buffer = new byte[1024];
            int length;
            try (InputStream inputStream = connection.getInputStream();
                 ByteArrayOutputStream result = new ByteArrayOutputStream()) {

                while ((length = inputStream.read(buffer)) > 0) {
                    result.write(buffer, 0, length);
                }
                return result.toString("UTF-8");
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
