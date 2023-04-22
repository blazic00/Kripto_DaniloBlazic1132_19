package crypto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Crypto {
    public static boolean checkIfUsernameExists(String username) {
        return false;
    }

    public static void createUser(String ime, String mail, String username, String pass1) {
      /*  try {
            ProcessBuilder pb = new ProcessBuilder("bash", "scripts/genUser.sh",username,pass1,ime,mail,MetaData.getCApassword());
            pb.directory(new File(System.getProperty("user.dir")));
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String[] command = {"bash","scripts/genUser.sh",username,pass1,ime,mail,MetaData.getCApassword()};
        runScript(command);
    }

    private static int checkIfUserExist(String username){
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
}
