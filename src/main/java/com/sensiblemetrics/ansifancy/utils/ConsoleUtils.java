/*
 * The MIT License
 *
 * Copyright 2019 SensibleMetrics Labs, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.ansifancy.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.Objects;

/**
 * Console utilities implementation
 */
@Slf4j
@UtilityClass
public class ConsoleUtils {

    // ---Constants------------------------------------------------
    public static final char ESC = 27;

    // Text attributes
    public static final int NORMAL = 0;

    public static final int BOLD = 1;

    public static final int UNDERSCORE = 4;

    public static final int BLINK = 5;

    public static final int REVERSE = 7;

    public static final int CONCEALED = 8;

    // Foreground colors
    public static final int FG_BLACK = 30;

    public static final int FG_RED = 31;

    public static final int FG_GREEN = 32;

    public static final int FG_YELLOW = 33;

    public static final int FG_BLUE = 34;

    public static final int FG_MAGENTA = 35;

    public static final int FG_CYAN = 36;

    public static final int FG_WHITE = 37;

    // Background colors
    public static final int BG_BLACK = 40;

    public static final int BG_RED = 41;

    public static final int BG_GREEN = 42;

    public static final int BG_YELLOW = 43;

    public static final int BG_BLUE = 44;

    public static final int BG_MAGENTA = 45;

    public static final int BG_CYAN = 46;

    public static final int BG_WHITE = 47;

    /**
     * Default windows console flag
     */
    public static final boolean IS_WINDOWS_CONSOLE = System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("win");

    /**
     * Default cygwin console flag
     */
    public static final boolean IS_CYGWIN = IS_WINDOWS_CONSOLE
        && Objects.nonNull(System.getenv("PWD"))
        && System.getenv("PWD").startsWith("/")
        && !Objects.equals("cygwin", System.getenv("TERM"));

    /**
     * Default mingw console flag
     */
    public static final boolean IS_MINGW_XTERM = IS_WINDOWS_CONSOLE
        && Objects.nonNull(System.getenv("MSYSTEM"))
        && System.getenv("MSYSTEM").startsWith("MINGW")
        && Objects.equals("xterm", System.getenv("TERM"));

    /*
     * A method to change the color codes. This only works on color-enabled
     * terminals. In Windows/MS-DOS you need to load the ansi.sys driver from
     * config.sys or c:\winnt\system32\config.nt (NT/win2k). ANSI.sys only works
     * under Win2k in DOS mode. In UNIX, you need an ansi-enabled terminal...
     */
    public String getColor(final int style, final int fgColor, final int bgColor) {
        return ESC + "[" + style + ";" + fgColor + ";" + bgColor + "m";
    }

    public String resetColor() {
        return getColor(NORMAL, FG_WHITE, BG_BLACK);
    }

    public String treeColor() {
        return getColor(NORMAL, FG_YELLOW, BG_BLACK);
    }
}
