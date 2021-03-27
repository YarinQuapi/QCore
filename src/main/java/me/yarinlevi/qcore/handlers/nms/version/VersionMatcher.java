package me.yarinlevi.qcore.handlers.nms.version;

import me.yarinlevi.qcore.QCore;
import me.yarinlevi.qcore.handlers.nms.version.versions.Wrapper1_12_R1;
import me.yarinlevi.qcore.handlers.nms.version.versions.Wrapper1_14_R1;
import me.yarinlevi.qcore.handlers.nms.version.versions.Wrapper1_16_R3;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.List;

/**
 * @author Quapi
 */
public class VersionMatcher {
    private final String serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

    private final List<Class<? extends VersionWrapper>> versions = Arrays.asList(
            Wrapper1_16_R3.class, // 1.16.4 - 1.16.5
            Wrapper1_14_R1.class, // 1.14.4
            Wrapper1_12_R1.class // 1.12.2
    );

    public VersionWrapper match() {
        try {
            return versions.stream()
                    .filter(version -> version.getSimpleName().substring(7).equals(serverVersion))
                    .findFirst().orElseThrow(() -> new RuntimeException("Your server version is not supported by QUtils version: " + QCore.getInstance().getDescription().getVersion() + "!"))
                    .newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
