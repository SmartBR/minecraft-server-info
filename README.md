# minecraft-server-info
API to obtain information from a minecraft server.

# How to install
```groovy
repositories {
    mavenCentral()

    maven {
        url = 'https://jitpack.io'
    }
}

dependencies  {
    compile 'com.github.SmartBR:minecraft-server-info:-SNAPSHOT'
}
```

# Examples
```java
class Test {

    public static void main(String[] args) {
        MCServerInfo mcserver = new MCServerInfo();

        mcserver.getServer("mc.hypixel.net").whenComplete((serverResponse, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
                return;
            }

            System.out.println(serverResponse.toString());
        });
    }
}
```

#### Json Information:
```json
{
  "online": true,
  "ip": "127.0.0.1",
  "port": 25565,
  "hostname": "dc-server",
  "version": "1.7x-1.16x",
  "onlinePlayers": 150000,
  "maxPlayers": 200000
}
```