package crypto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Crypto {

    public static void createUser(String ime, String mail, String username, String pass1) {

        String[] command = {"bash","scripts/genUser.sh",username,pass1,ime,mail,MetaData.getCApassword()};
        runScript(command);
    }

    public static int checkIfUserExist(String username){
        String[] command = {"bash","scripts/checkIfUserExist.sh",username};
        return runScript(command);
    }



    private static int runScript(String[] command){
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(System.getProperty("user.dir")));
            Process p = pb.start();
            int exitCode = p.waitFor();
            return exitCode;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static int verifyPassword(String password, String username) {
        String[] command = {"bash","scripts/verifyPassword.sh",username,password};
        return runScript(command);
    }

    public static int verifyUserCert(String userCert) {
        String[] command = {"bash","scripts/verifyUserCert.sh",userCert};
        return runScript(command);
    }

    public static void revokeUserCert(String username){
        String userCert = username+".crt";
        String[] command = {"bash","scripts/revokeUserCert.sh",userCert,MetaData.getCApassword()};
        runScript(command);
    }

    public static void reactivateUserCert(String username){
        String userCert = username+".crt";
        String[] command = {"bash","scripts/reactivateUserCert.sh",userCert,MetaData.getCApassword()};
        runScript(command);
    }
}
