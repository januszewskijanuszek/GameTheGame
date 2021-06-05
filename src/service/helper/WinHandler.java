package service.helper;

public class WinHandler {
    private static boolean won;
    private static boolean surrender;

    public static void setSurrender(boolean surrender) {
        WinHandler.surrender = surrender;
    }

    public static boolean isSurrender() {
        return surrender;
    }

    public static void setWon(boolean won) {
        WinHandler.won = won;
    }

    public static boolean isWon() {
        return won;
    }
}
