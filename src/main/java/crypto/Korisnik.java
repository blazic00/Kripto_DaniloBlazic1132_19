package crypto;

public class Korisnik {

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentUser) {
        Korisnik.currentUser = currentUser;
    }

    private static String currentUser;

}
