/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
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
package com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.helpers.Color;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import lombok.*;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Default {@link Point} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class DefaultPoint implements Point {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 3840718954768956199L;

    /**
     * Default escape format
     */
    private static final String DEFAULT_ESCAPE_FORMAT = ((char) 27) + "[%sm";
    /**
     * Default cursor service escape format
     */
    private static final String DEFAULT_CURSOR_SERVICE_ESCAPE_FORMAT = ((char) 27) + "[%s";
    /**
     * Default cursor escape format
     */
    private static final String DEFAULT_CURSOR_ESCAPE_FORMAT = ((char) 27) + "[%s%s";
    /**
     * Default position cursor escape format
     */
    private static final String DEFAULT_CURSOR_POSITION_ESCAPE_FORMAT = ((char) 27) + "[%s;%s%s";
    /**
     * Default light color escape format
     */
    private static final String DEFAULT_BRIGHT_COLOR_ESCAPE_FORMAT = ((char) 27) + "[%s;1m";
    /**
     * Default rgb escape format
     */
    private static final String DEFAULT_RGB_COLOR_ESCAPE_FORMAT = ((char) 27) + "[%s;2;%s;%s;%sm";
    /**
     * Default foreground color escape format
     */
    private static final String DEFAULT_FOREGROUND_COLOR_ESCAPE_FORMAT = ((char) 27) + "[38;5;%sm";
    /**
     * Default background color escape format
     */
    private static final String DEFAULT_BACKGROUND_COLOR_ESCAPE_FORMAT = ((char) 27) + "[48;5;%sm";

    /**
     * Default cursor position type
     */
    enum CursorPositionType {
        A, B, C, D, J, K, E, F, G, H
    }

    /**
     * Default cursor clear type
     */
    enum CursorClearType {
        END, BEGIN, ALL
    }

    /**
     * Default cursor escape {@link Point} functional points
     */
    public static final IntFunction<Point> CURSOR_UP = num -> getCursorPoint("CURSOR_UP", "CUR_UP", num, CursorPositionType.A);
    public static final IntFunction<Point> CURSOR_DOWN = num -> getCursorPoint("CURSOR_DOWN", "CUR_DWN", num, CursorPositionType.B);
    public static final IntFunction<Point> CURSOR_RIGHT = num -> getCursorPoint("CURSOR_RIGHT", "CUR_RGHT", num, CursorPositionType.C);
    public static final IntFunction<Point> CURSOR_LEFT = num -> getCursorPoint("CURSOR_LEFT", "CUR_LFT", num, CursorPositionType.D);
    /**
     * Default clear screen escape {@link Point} points
     */
    public static final Point CLEAR_SCREEN_END = getCursorPoint("CLEAR_SCREEN_END", "CLR_SCR_E", CursorClearType.END.ordinal(), CursorPositionType.J);
    public static final Point CLEAR_SCREEN_BEGIN = getCursorPoint("CLEAR_SCREEN_BEGIN", "CLR_SCR_B", CursorClearType.BEGIN.ordinal(), CursorPositionType.J);
    public static final Point CLEAR_SCREEN_ALL = getCursorPoint("CLEAR_SCREEN_ALL", "CLR_SCR_ALL", CursorClearType.ALL.ordinal(), CursorPositionType.J);
    /**
     * Default clear line escape {@link Point} points
     */
    public static final Point CLEAR_LINE_END = getCursorPoint("CLEAR_LINE_END", "CLR_LN_E", CursorClearType.END.ordinal(), CursorPositionType.K);
    public static final Point CLEAR_LINE_BEGIN = getCursorPoint("CLEAR_LINE_BEGIN", "CLR_LN_B", CursorClearType.BEGIN.ordinal(), CursorPositionType.K);
    public static final Point CLEAR_LINE_ALL = getCursorPoint("CLEAR_LINE_ALL", "CLR_LN_ALL", CursorClearType.ALL.ordinal(), CursorPositionType.K);
    /**
     * Default position cursor escape {@link Point} functional ponts
     */
    public static final IntFunction<Point> CURSOR_NEXT_LINE = num -> getCursorPoint("NEXT_LINE", "N_LN", num, CursorPositionType.E);
    public static final IntFunction<Point> CURSOR_PREV_LINE = num -> getCursorPoint("PREV_LINE", "P_LN", num, CursorPositionType.F);
    public static final IntFunction<Point> CURSOR_COLUMN = num -> getCursorPoint("COLUMN", "COL", num, CursorPositionType.G);
    /**
     * Default service cursor escape {@link Point} points
     */
    public static final BiFunction<Integer, Integer, Point> CURSOR_POSITION = (row, column) -> getPositionCursorPoint("POSITION", "POS", row, column);
    public static final Point CURSOR_SAVE = getServiceCursorPoint("SAVE", "SV", 's');
    public static final Point CURSOR_RESTORE = getServiceCursorPoint("RESTORE", "RSTR", 'u');
    /**
     * Default decoration escape {@link Point} points
     */
    public static final Point BOLD = getPoint("BOLD", "BLD", 1, Type.DECORATION_CONTROL);
    public static final Point FAINT = getPoint("FAINT", "FNT", 2, Type.DECORATION_CONTROL);
    public static final Point INVERSE = getPoint("INVERSE", "INV", 3, Type.DECORATION_CONTROL);
    public static final Point UNDERLINE = getPoint("UNDERLINE", "ULN", 4, Type.DECORATION_CONTROL);
    public static final Point SLOW_BLINK = getPoint("SLOW_BLINK", "S_BLK", 5, Type.DECORATION_CONTROL);
    public static final Point RAPID_BLINK = getPoint("RAPID_BLINK", "R_BLK", 6, Type.DECORATION_CONTROL);
    public static final Point REVERSE = getPoint("REVERSE", "REV", 7, Type.DECORATION_CONTROL);
    public static final Point BLANK = getPoint("BLANK", "BLN", 8, Type.DECORATION_CONTROL);
    public static final Point OVERSTRIKE = getPoint("OVERSTRIKE", "OSTRK", 9, Type.DECORATION_CONTROL);
    public static final Point FRAMED = getPoint("FRAMED", "FRM", 51, Type.DECORATION_CONTROL);
    public static final Point CIRCLED = getPoint("CIRCLED", "CRC", 52, Type.DECORATION_CONTROL);
    public static final Point OVERLINE = getPoint("OVERLINE", "OLN", 53, Type.DECORATION_CONTROL);
    /**
     * Default ideogram escape {@link Point} points
     */
    public static final Point RIGHT_SIDE_LINE = getPoint("RIGHT_SIDE_LINE", "R_SD_LN", 60, Type.IDEOGRAM);
    public static final Point RIGHT_SIDE_DOUBLE_LINE = getPoint("RIGHT_SIDE_DOUBLE_LINE", "R_SD_DLN", 61, Type.IDEOGRAM);
    public static final Point LEFT_SIDE_LINE = getPoint("LEFT_SIDE_LINE", "L_SD_LN", 62, Type.IDEOGRAM);
    public static final Point LEFT_SIDE_DOUBLE_LINE = getPoint("LEFT_SIDE_DOUBLE_LINE", "L_SD_DLN", 63, Type.IDEOGRAM);
    public static final Point STRESS_MARKING = getPoint("STRESS_MARKING", "STRSS", 64, Type.IDEOGRAM);
    /**
     * Default service escape {@link Point} points
     */
    public static final Point RESET = getPoint("RESET", "RST", 0, Type.SERVICE_CONTROL);
    public static final Point FRAKTUR = getPoint("FRAKTUR", "FRKTR", 20, Type.SERVICE_CONTROL);
    public static final Point RESET_BOLD = getPoint("RESET_BOLD", "RST_BLD", 21, Type.SERVICE_CONTROL);
    public static final Point RESET_COLOR = getPoint("RESET_COLOR", "RST_CLR", 22, Type.SERVICE_CONTROL);
    public static final Point RESET_ITALIC = getPoint("RESET_ITALIC", "RST_IT", 23, Type.SERVICE_CONTROL);
    public static final Point RESET_UNDERLINE = getPoint("RESET_UNDERLINE", "RST_ULN", 24, Type.SERVICE_CONTROL);
    public static final Point RESET_BLINK = getPoint("RESET_BLINK", "RST_BLNK", 25, Type.SERVICE_CONTROL);
    public static final Point RESET_INVERSE = getPoint("RESET_INVERSE", "RST_INV", 27, Type.SERVICE_CONTROL);
    public static final Point RESET_BLANK = getPoint("RESET_BLANK", "RST_BLNK", 28, Type.SERVICE_CONTROL);
    public static final Point RESET_STRIKE = getPoint("RESET_STRIKE", "RST_STRK", 29, Type.SERVICE_CONTROL);
    public static final Point RESET_FRAMED = getPoint("RESET_FRAMED", "RST_FRM", 54, Type.SERVICE_CONTROL);
    public static final Point RESET_OVERLINE = getPoint("RESET_OVERLINE", "RST_OLN", 55, Type.SERVICE_CONTROL);
    public static final Point RESET_IDEOGRAM = getPoint("RESET_IDEOGRAM", "RST_IDEO", 65, Type.SERVICE_CONTROL);
    public static final Point RESET_FOREGROUND = getPoint("RESET_FOREGROUND", "RST_FG", 39, Type.SERVICE_CONTROL);
    public static final Point RESET_BACKGROUND = getPoint("RESET_BACKGROUND", "RST_BG", 49, Type.SERVICE_CONTROL);
    /**
     * Default foreground color escape {@link Point} points
     */
    public static final Point BlACK_FOREGROUND = getPoint("BlACK_FOREGROUND", "BLACK_FG", 30, Type.FOREGROUND_COLOR);
    public static final Point RED_FOREGROUND = getPoint("RED_FOREGROUND", "RED_FG", 31, Type.FOREGROUND_COLOR);
    public static final Point GREEN_FOREGROUND = getPoint("GREEN_FOREGROUND", "GREEN_FG", 32, Type.FOREGROUND_COLOR);
    public static final Point YELLOW_FOREGROUND = getPoint("YELLOW_FOREGROUND", "YELLOW_FG", 33, Type.FOREGROUND_COLOR);
    public static final Point BLUE_FOREGROUND = getPoint("BLUE_FOREGROUND", "BLUE_FG", 34, Type.FOREGROUND_COLOR);
    public static final Point MAGENTA_FOREGROUND = getPoint("MAGENTA_FOREGROUND", "MAGENTA_FG", 35, Type.FOREGROUND_COLOR);
    public static final Point CYAN_FOREGROUND = getPoint("CYAN_FOREGROUND", "CYAN_FG", 36, Type.FOREGROUND_COLOR);
    public static final Point WHITE_FOREGROUND = getPoint("WHITE_FOREGROUND", "WHITE_FG", 37, Type.FOREGROUND_COLOR);
    /**
     * Default foreground bright color escape {@link Point} points
     */
    public static final Point BRIGHT_BLACK_FOREGROUND = getBrightColorPoint("BRIGHT_BLACK_FOREGROUND", "BR_BLACK_FG", 30, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_RED_FOREGROUND = getBrightColorPoint("BRIGHT_RED_FOREGROUND", "BR_RED_FG", 31, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_GREEN_FOREGROUND = getBrightColorPoint("BRIGHT_GREEN_FOREGROUND", "BR_GREEN_FG", 32, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_YELLOW_FOREGROUND = getBrightColorPoint("BRIGHT_YELLOW_FOREGROUND", "BR_YELLOW_FG", 33, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_BLUE_FOREGROUND = getBrightColorPoint("BRIGHT_BLUE_FOREGROUND", "BR_BLUE_FG", 34, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_MAGENTA_FOREGROUND = getBrightColorPoint("BRIGHT_MAGENTA_FOREGROUND", "BR_MAGENTA_FG", 35, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_CYAN_FOREGROUND = getBrightColorPoint("BRIGHT_CYAN_FOREGROUND", "BR_CYAN_FG", 36, Type.FOREGROUND_COLOR);
    public static final Point BRIGHT_WHITE_FOREGROUND = getBrightColorPoint("BRIGHT_WHITE_FOREGROUND", "BR_WHITE_FG", 37, Type.FOREGROUND_COLOR);
    /**
     * Default background color escape {@link Point} points
     */
    public static final Point BLACK_BACKGROUND = getPoint("BLACK_BACKGROUND", "BLACK_BG", 40, Type.BACKGROUND_COLOR);
    public static final Point RED_BACKGROUND = getPoint("RED_BACKGROUND", "RED_BG", 41, Type.BACKGROUND_COLOR);
    public static final Point GREEN_BACKGROUND = getPoint("GREEN_BACKGROUND", "GREEN_BG", 44, Type.BACKGROUND_COLOR);
    public static final Point YELLOW_BACKGROUND = getPoint("YELLOW_BACKGROUND", "YELLOW_BG", 43, Type.BACKGROUND_COLOR);
    public static final Point BLUE_BACKGROUND = getPoint("BLUE_BACKGROUND", "BLUE_BG", 44, Type.BACKGROUND_COLOR);
    public static final Point MAGENTA_BACKGROUND = getPoint("MAGENTA_BACKGROUND", "MAGENTA_BG", 45, Type.BACKGROUND_COLOR);
    public static final Point CYAN_BACKGROUND = getPoint("CYAN_BACKGROUND", "CYAN_BG", 46, Type.BACKGROUND_COLOR);
    public static final Point WHITE_BACKGROUND = getPoint("WHITE_BACKGROUND", "WHITE_BG", 47, Type.BACKGROUND_COLOR);
    /**
     * Default background bright color escape {@link Point} points
     */
    public static final Point BRIGHT_BLACK_BACKGROUND = getBrightColorPoint("BRIGHT_BLACK_BACKGROUND", "BR_BLACK_BG", 40, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_RED_BACKGROUND = getBrightColorPoint("BRIGHT_RED_BACKGROUND", "BR_RED_BG", 41, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_GREEN_BACKGROUND = getBrightColorPoint("BRIGHT_GREEN_BACKGROUND", "BR_GREEN_BG", 44, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_YELLOW_BACKGROUND = getBrightColorPoint("BRIGHT_YELLOW_BACKGROUND", "BR_YELLOW_BG", 43, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_BLUE_BACKGROUND = getBrightColorPoint("BRIGHT_BLUE_BACKGROUND", "BR_BLUE_BG", 44, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_MAGENTA_BACKGROUND = getBrightColorPoint("BRIGHT_MAGENTA_BACKGROUND", "BR_MAGENTA_BG", 45, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_CYAN_BACKGROUND = getBrightColorPoint("BRIGHT_CYAN_BACKGROUND", "BR_CYAN_BG", 46, Type.BACKGROUND_COLOR);
    public static final Point BRIGHT_WHITE_BACKGROUND = getBrightColorPoint("BRIGHT_WHITE_BACKGROUND", "BR_WHITE_BG", 47, Type.BACKGROUND_COLOR);

    /**
     * Default point name
     */
    private CharSequence name;

    /**
     * Default point symbol
     */
    private CharSequence symbol;

    /**
     * Default point code
     */
    private CharSequence code;

    /**
     * Default point type
     */
    private Type type;

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code
     * @param type   - initial input type
     * @return new {@link Point} instance
     */
    public static Point getPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int code, final Type type) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_ESCAPE_FORMAT, code))
            .type(type)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input code {@link CharSequence}
     * @param color  - initial input color {@link Color}
     * @param type   - initial input color type
     * @return new {@link Point} instance
     */
    public static Point getRGBPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final Color color, final Type type) {
        if (isEmpty(symbol)) {
            Color co;
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_RGB_COLOR_ESCAPE_FORMAT, color.getRed(), color.getGreen(), color.getBlue()))
            .type(type)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link Point} instance
     */
    public static Point getForegroundColor(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final CharSequence code) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_FOREGROUND_COLOR_ESCAPE_FORMAT, code))
            .type(Type.FOREGROUND_COLOR)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link Point} instance
     */
    public static Point getBackgroundColor(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final CharSequence code) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_BACKGROUND_COLOR_ESCAPE_FORMAT, code))
            .type(Type.BACKGROUND_COLOR)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link Point} instance
     */
    private static Point getBrightColorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int code, final Type type) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_BRIGHT_COLOR_ESCAPE_FORMAT, code))
            .type(type)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name     - initial input name {@link CharSequence}
     * @param symbol   - initial input symbol {@link CharSequence}
     * @param position - initial input cursor position
     * @param type     - initial input cursor type
     * @return new {@link Point} instance
     */
    public static Point getCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int position, final CursorPositionType type) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_CURSOR_ESCAPE_FORMAT, position, type))
            .type(Type.CURSOR_CONTROL)
            .build();
    }

    /**
     * Returns new {@link Point} instance by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param row    - initial input row position
     * @param column - initial input column position
     * @return new {@link Point} instance
     */
    public static Point getPositionCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int row, final int column) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_CURSOR_POSITION_ESCAPE_FORMAT, row, column, CursorPositionType.H))
            .type(Type.CURSOR_CONTROL)
            .build();
    }

    /**
     * Returns new service {@link Point} by input data parameters
     *
     * @param name   - initial input name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input cursor service code
     * @return new {@link Point} instance
     */
    public static Point getServiceCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final char code) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .name(name)
            .symbol(symbol)
            .code(String.format(DEFAULT_CURSOR_SERVICE_ESCAPE_FORMAT, code))
            .type(Type.CURSOR_CONTROL)
            .build();
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public char charAt(int index) {
        return 0;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return null;
    }
}
