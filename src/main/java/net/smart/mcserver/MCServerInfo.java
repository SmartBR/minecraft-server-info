package net.smart.mcserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.smart.mcserver.reply.ServerResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MCServerInfo {

    private final Gson GSON = new GsonBuilder().create();
    private final String BASE_URL = "https://api.mcsrvstat.us/2/";

    private final ExecutorService executorService;
    private final HttpClient httpClient;

    public MCServerInfo() {
        executorService = Executors.newCachedThreadPool();
        httpClient = HttpClientBuilder.create().build();
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public CompletableFuture<ServerResponse> getServer(String serverAddress) {
        CompletableFuture<ServerResponse> future = new CompletableFuture<>();
        try {
            executorService.submit(() -> {
                try {
                    JsonObject response = httpClient.execute(new HttpGet(BASE_URL + serverAddress), object ->
                            GSON.fromJson(EntityUtils.toString(object.getEntity(), "UTF-8"), JsonObject.class)
                    );

                    future.complete(new ServerResponse(
                            response.get("online").getAsBoolean(),
                            response.get("ip").getAsString(),
                            response.get("port").getAsInt(),
                            response.get("hostname").getAsString(),
                            response.get("version").getAsString(),
                            response.getAsJsonObject("players").get("online").getAsInt(),
                            response.getAsJsonObject("players").get("max").getAsInt()
                    ));
                }catch (Throwable throwable) {
                    future.completeExceptionally(throwable);
                }
            });
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return future;
    }
}
