package employee;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static okhttp3.Protocol.HTTP_1_1;

public class GoogleHttpClient {

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .build();

    private MediaType APPLICATION_JSON = MediaType.parse("application/json");

    public Response post(String url, String body) {
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(APPLICATION_JSON, body))
                .build();

        return doRequest(request);
    }

    private Response doRequest(Request request) {
        try {
            return client
                    .newCall(request)
                    .execute();
        } catch (IOException e) {
            return new Response.Builder()
                    .request(request)
                    .protocol(HTTP_1_1)
                    .code(500)
                    .build();
        }
    }
}