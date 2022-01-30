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

    private static void downloadFile(URL url, String fileName) throws Exception {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(fileName));
            System.out.println(Paths.get(fileName));
        }
    }

    public static void checkExist() {
        System.out.println("[FeatherAddons] Dar Hale Check Kardan Addon Ha...");
        final Path plugins = Paths.get((new File(".").getAbsolutePath() + "\\plugins\\FeatherUtilities.jar").replace(".\\", ""));
        if (Files.notExists(plugins)) {
            System.out.println("[FeatherAddons] Addone FeatherUtilities Peyda Nashod, Dar Hale Download...");
            download();
        } else {
            System.out.println("[FeatherAddons] Addon Ha Ba Movafaghiat Load Shodand");
        }
    }

    public static void download() {
        URL url = null;

        try {
            url = new URL("https://cdn.feathermc.ir/addons/FeatherUtilities/latest/FeatherUtilities.jar");
        } catch (MalformedURLException e) {
            System.out.println("[FeatherAddons] Khata Dar Check Kardan Addon Ha");
        }

        try {
            downloadFile(url, "FeatherUtilities.jar");
        } catch (Exception e) {
            System.out.println("[FeatherAddons] Khata Dar Downloade File");
        }

        final Path jar = Paths.get(new File(".").getAbsolutePath() + "\\FeatherUtilities.jar");
        final Path plugins = Paths.get("C:\\Users\\Sxhqil\\Desktop\\Testing\\plugins\\FeatherUtilities.jar");

        try {
            Files.move(jar, plugins, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            System.out.println("[FeatherAddons] Khata Dar Enteqale File");
        }

    }
}
