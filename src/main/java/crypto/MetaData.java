package crypto;

import java.io.File;

public class MetaData {
    public static String getPathToCerts() {
        return pathToCerts;
    }

    private static String pathToCerts = "CA"+ File.separator+"certs";

    public static String getCApassword() {
        return CApassword;
    }

    private static String CApassword="sigurnost";
}
