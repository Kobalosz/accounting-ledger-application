package util;

public class Colors {

    // resetting the characters back to their original color (Didn't know I needed to do that)
    public static final String RESET      = "\u001B[0m";

    // text styles
    public static final String BOLD       = "\u001B[1m";
    public static final String DIM        = "\u001B[2m";
    public static final String ITALIC     = "\u001B[3m";
    public static final String UNDERLINE  = "\u001B[4m";

    // foreground colors
    public static final String RED        = "\u001B[31m";
    public static final String GREEN      = "\u001B[32m";
    public static final String YELLOW     = "\u001B[33m";
    public static final String CYAN       = "\u001B[36m";
    public static final String WHITE      = "\u001B[37m";

    // brighter foreground colors
    public static final String BRIGHT_RED    = "\u001B[91m";
    public static final String BRIGHT_GREEN  = "\u001B[92m";

    // background colors
    public static final String BG_RED     = "\u001B[41m";
    public static final String BG_GREEN   = "\u001B[42m";



    // Wrapper for text I'd like to color
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }

    // Wrapper for strings that I'd like to embolden
    public static String bold(String text, String color) {
        return BOLD + color + text + RESET;
    }

    // Error backgrounds
    public static String error(String text) {
        return BOLD + WHITE + BG_RED + " " + text + " " + RESET;
    }

    // Success backgrounds
    public static String success(String text) {
        return BOLD + WHITE + BG_GREEN + " " + text + " " + RESET;
    }
}