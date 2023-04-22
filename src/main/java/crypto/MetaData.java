package crypto;

import java.io.File;

public class MetaData {
    public static String getPathToCerts() {
        return pathToCerts;
    }

    public static int getLoginCounter() {
        return loginCounter;
    }

    private static int loginCounter = 3;
    private static String pathToCerts = "CA"+ File.separator+"certs";

    public static String getCApassword() {
        return CApassword;
    }

    private static String CApassword="sigurnost";
}
