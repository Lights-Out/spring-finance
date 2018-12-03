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
        String url = REQUEST_URI + path;

//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();

        byte[] buffer = new byte[1024];
        int length;
        try (InputStream inputStream = getContent(url);
             ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            while ((length = inputStream.read(buffer)) > 0) {
                result.write(buffer, 0, length);
            }
            return result.toString("UTF-8");
        }
    }

    public InputStream getContent(String uri) throws IOException {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            return connection.getInputStream();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
