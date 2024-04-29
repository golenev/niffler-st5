package guru.qa.niffler.model;

public record NifflerUser(String username, String password) {

    public static final NifflerUser NIFFLER_DIMA = new NifflerUser("dima", "12345");
    public static final NifflerUser NIFFLER_QWERTY = new NifflerUser("qwerty", "12345");
}
