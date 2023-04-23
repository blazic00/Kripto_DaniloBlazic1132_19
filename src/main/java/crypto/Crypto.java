package crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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


    public static void signFile(String username,File selectedFile) {
        String pomFilePath = selectedFile.getAbsolutePath().replace(""+File.separatorChar,"/");
        char drive = pomFilePath.toLowerCase().charAt(0);
        pomFilePath = pomFilePath.replaceAll("\\w:","/mnt/"+drive);
        String[] command = {"bash","scripts/signFile.sh",username,pomFilePath,selectedFile.getName()};
        runScript(command);
    }

    private static void splitFile(File selectedFile) throws IOException {
        byte[] bytes = Files.readAllBytes(selectedFile.toPath());
        Random random = new Random();
        int segments = random.nextInt(4,10);
        int segmentSize = bytes.length/segments;
        int lastSegmentSize = segmentSize + bytes.length%segments;

        byte[] pom;
        for(int i=0;i<segments;i++){
            if(i<segments-1) {
                pom = new byte[segmentSize];
                System.arraycopy(bytes, i * segmentSize, pom, 0, segmentSize);
            }
            else{
                pom = new byte[lastSegmentSize];
                System.arraycopy(bytes, i * segmentSize, pom, 0, lastSegmentSize);
            }
            File file = new File("tempFileSegments"+File.separator+selectedFile.getName()+"."+Korisnik.getCurrentUser()+"."+(i+1));
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(pom);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void assembleFile(String selectedFileName) throws IOException {
        File file = new File(MetaData.getPathTotempFileSegments());
        int size=0;
        int segmentSize= (int)  (int) file.listFiles()[0].length();

        for(int i =0;i<file.list().length;i++){
            int segmentSize1= (int) file.listFiles()[i].length();
            size+=segmentSize1;
        }
        byte[] fileBytes = new byte[size];

        for(int i =0;i<file.list().length;i++){
            System.arraycopy(Files.readAllBytes(file.listFiles()[i].toPath()),0,fileBytes,i*segmentSize,(int)file.listFiles()[i].length());
        }
        try (FileOutputStream outputStream = new FileOutputStream(MetaData.getPathToDownloadFolder()+File.separator+selectedFileName)) {
            outputStream.write(fileBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deleteContentOfTempFileSegments(){
        File folder = new File(MetaData.getPathTotempFileSegments());

        // Check if the folder exists
        if (!folder.exists()) {
            return;
        }
        // Get the contents of the folder
        File[] files = folder.listFiles();

        // Delete each file in the folder
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }


    public static void encryptFile(File selectedFile) throws IOException {
       splitFile(selectedFile);
        String[] command = {"bash","scripts/encryptFile.sh",MetaData.getAESpassword()};
        runScript(command);
        deleteContentOfTempFileSegments();
    }

    private static void decryptFileSegments(String selectedFileName){
        String[] command = {"bash","scripts/decryptFileSegments.sh",Korisnik.getCurrentUser(),selectedFileName,MetaData.getAESpassword()};
        runScript(command);
    }


    public static int download(String selectedFileName) throws IOException {
        decryptFileSegments(selectedFileName);
        assembleFile(selectedFileName);
        deleteContentOfTempFileSegments();
        return verifyFilesignature("./"+MetaData.getPathToDownloadFolder()+"/"+selectedFileName,selectedFileName);
    }

    private static int verifyFilesignature(String downloadFile,String selectedFileName) {
        String[] command = {"bash","scripts/verifyFilesignature.sh",Korisnik.getCurrentUser(),downloadFile,selectedFileName};
        return runScript(command);
    }
}
