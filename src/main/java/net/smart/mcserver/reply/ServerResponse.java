package net.smart.mcserver.reply;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ServerResponse {

    @Getter private final boolean online;
    @Getter private final String ip;
    @Getter private final int port;
    @Getter private final String hostname;
    @Getter private final String version;
    @Getter private final int onlinePlayers;
    @Getter private final int maxPlayers;

    @Override
    public String toString() {
        return "ServerResponse{" +
                "online=" + online +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", hostname='" + hostname + '\'' +
                ", version='" + version + '\'' +
                ", onlinePlayers=" + onlinePlayers +
                ", maxPlayers=" + maxPlayers +
                '}';
    }
}
