package util;

public class Colors {

    // ── Reset ──────────────────────────────────────────────
    public static final String RESET      = "\u001B[0m";

    // ── Text styles ────────────────────────────────────────
    public static final String BOLD       = "\u001B[1m";
    public static final String DIM        = "\u001B[2m";
    public static final String ITALIC     = "\u001B[3m";
    public static final String UNDERLINE  = "\u001B[4m";

    // ── Foreground colors ──────────────────────────────────
    public static final String BLACK      = "\u001B[30m";
    public static final String RED        = "\u001B[31m";
    public static final String GREEN      = "\u001B[32m";
    public static final String YELLOW     = "\u001B[33m";
    public static final String BLUE       = "\u001B[34m";
    public static final String PURPLE     = "\u001B[35m";
    public static final String CYAN       = "\u001B[36m";
    public static final String WHITE      = "\u001B[37m";

    // ── Bright foreground colors ───────────────────────────
    public static final String BRIGHT_RED    = "\u001B[91m";
    public static final String BRIGHT_GREEN  = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE   = "\u001B[94m";
    public static final String BRIGHT_CYAN   = "\u001B[96m";
    public static final String BRIGHT_WHITE  = "\u001B[97m";

    // ── Background colors ──────────────────────────────────
    public static final String BG_BLACK   = "\u001B[40m";
    public static final String BG_RED     = "\u001B[41m";
    public static final String BG_GREEN   = "\u001B[42m";
    public static final String BG_YELLOW  = "\u001B[43m";
    public static final String BG_BLUE    = "\u001B[44m";
    public static final String BG_PURPLE  = "\u001B[45m";
    public static final String BG_CYAN    = "\u001B[46m";
    public static final String BG_WHITE   = "\u001B[47m";

    // ── Helper methods ─────────────────────────────────────

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