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

    public static int getMinSegments() {
        return minSegments;
    }

    private static int minSegments = 4;

    public static int getMaxSegments() {
        return maxSegments;
    }

    private static int maxSegments = 9;
    private static String pathToCerts = "CA"+ File.separator+"certs";

    public static String getPathToSignedFiles() {
        return pathToSignedFiles;
    }

    private static String pathToSignedFiles = "signedFiles";

    public static String getPathTotempFileSegments() {
        return pathTotempFileSegments;
    }

    private static String pathTotempFileSegments = "tempFileSegments";

    public static String getPathToDownloadFolder() {
        return pathToDownloadFolder;
    }

    private static String pathToDownloadFolder = "download";

    public static String getCApassword() {
        return CApassword;
    }

    private static String CApassword="sigurnost";

    public static String getAESpassword() {
        return AESpassword;
    }

    private static String AESpassword="sigurnost";
}
