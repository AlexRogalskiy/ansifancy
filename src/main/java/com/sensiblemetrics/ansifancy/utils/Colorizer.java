package com.sensiblemetrics.ansifancy.utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Colorizer {
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_BLACK = "\u001B[30m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_GREEN = "\u001B[32m";
    static final String ANSI_YELLOW = "\u001B[33m";
    static final String ANSI_BLUE = "\u001B[34m";
    static final String ANSI_PURPLE = "\u001B[35m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final String ANSI_WHITE = "\u001B[37m";

    private final boolean enabled;

    private static boolean isWindows() {
        return System.getProperty("os.name").startsWith("Windows");
    }

    public String error(final String input) {
        return colorise(ANSI_RED, input);
    }

    public String error(final int input) {
        return error(String.valueOf(input));
    }

    public String error(final long input) {
        return error(String.valueOf(input));
    }

    public String error(final boolean input) {
        return error(String.valueOf(input));
    }

    public String important(final String input) {
        return colorise(ANSI_YELLOW, input);
    }

    public String important(final int input) {
        return important(String.valueOf(input));
    }

    public String important(final long input) {
        return important(String.valueOf(input));
    }

    public String important(final boolean input) {
        return important(String.valueOf(input));
    }

    public String green(String input) {
        return colorise(ANSI_GREEN, input);
    }

    public String red(String input) {
        return colorise(ANSI_RED, input);
    }

    public String yellow(String input) {
        return colorise(ANSI_YELLOW, input);
    }

    public String blue(String input) {
        return colorise(ANSI_BLUE, input);
    }

    private String colorise(final String color, final String text) {
        if (!this.enabled || isWindows()) {
            return text;
        } else {
            return color + text + ANSI_RESET;
        }
    }

    public static double[] rgb2hsv(final int red, final int green, final int blue) {
        double computedH, computedS, computedV;
        double r, g, b;

        if (red < 0 || green < 0 || blue < 0 || red > 255 || green > 255 || blue > 255) {
            return null;
        }

        r = (double) red / 255;
        g = (double) green / 255;
        b = (double) blue / 255;

        double minRGB = Math.min(r, Math.min(g, b));
        double maxRGB = Math.max(r, Math.max(g, b));

        // Black-gray-white
        if (minRGB == maxRGB) {
            computedV = minRGB;
            return new double[]{0, 0, computedV};
        }

        // Colors other than black-gray-white:
        double d = (r == minRGB) ? g - b : ((b == minRGB) ? r - g : b - r);
        double h = (r == minRGB) ? 3 : ((b == minRGB) ? 1 : 5);
        computedH = 60 * (h - d / (maxRGB - minRGB));
        computedS = (maxRGB - minRGB) / maxRGB;
        computedV = maxRGB;
        return new double[]{computedH, computedS, computedV};
    }
}
