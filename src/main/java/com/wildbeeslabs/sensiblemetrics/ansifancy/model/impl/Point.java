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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PointIF;
import lombok.*;

import java.util.function.BiFunction;
import java.util.function.IntFunction;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Default {@link PointIF} implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Point implements PointIF {

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
        END,
        BEGIN,
        ALL
    }

    /**
     * Default cursor escape {@link PointIF} functional points
     */
    public static final IntFunction<PointIF> CURSOR_UP = num -> getCursorPoint("CURSOR_UP", "CUR_UP", num, CursorPositionType.A);
    public static final IntFunction<PointIF> CURSOR_DOWN = num -> getCursorPoint("CURSOR_DOWN", "CUR_DWN", num, CursorPositionType.B);
    public static final IntFunction<PointIF> CURSOR_RIGHT = num -> getCursorPoint("CURSOR_RIGHT", "CUR_RGHT", num, CursorPositionType.C);
    public static final IntFunction<PointIF> CURSOR_LEFT = num -> getCursorPoint("CURSOR_LEFT", "CUR_LFT", num, CursorPositionType.D);
    /**
     * Default clear screen escape {@link PointIF} points
     */
    public static final PointIF CLEAR_SCREEN_END = getCursorPoint("CLEAR_SCREEN_END", "CLR_SCR_E", CursorClearType.END.ordinal(), CursorPositionType.J);
    public static final PointIF CLEAR_SCREEN_BEGIN = getCursorPoint("CLEAR_SCREEN_BEGIN", "CLR_SCR_B", CursorClearType.BEGIN.ordinal(), CursorPositionType.J);
    public static final PointIF CLEAR_SCREEN_ALL = getCursorPoint("CLEAR_SCREEN_ALL", "CLR_SCR_ALL", CursorClearType.ALL.ordinal(), CursorPositionType.J);
    /**
     * Default clear line escape {@link PointIF} points
     */
    public static final PointIF CLEAR_LINE_END = getCursorPoint("CLEAR_LINE_END", "CLR_LN_E", CursorClearType.END.ordinal(), CursorPositionType.K);
    public static final PointIF CLEAR_LINE_BEGIN = getCursorPoint("CLEAR_LINE_BEGIN", "CLR_LN_B", CursorClearType.BEGIN.ordinal(), CursorPositionType.K);
    public static final PointIF CLEAR_LINE_ALL = getCursorPoint("CLEAR_LINE_ALL", "CLR_LN_ALL", CursorClearType.ALL.ordinal(), CursorPositionType.K);
    /**
     * Default position cursor escape {@link PointIF} functional points
     */
    public static final IntFunction<PointIF> CURSOR_NEXT_LINE = num -> getCursorPoint("NEXT_LINE", "N_LN", num, CursorPositionType.E);
    public static final IntFunction<PointIF> CURSOR_PREV_LINE = num -> getCursorPoint("PREV_LINE", "P_LN", num, CursorPositionType.F);
    public static final IntFunction<PointIF> CURSOR_COLUMN = num -> getCursorPoint("COLUMN", "COL", num, CursorPositionType.G);
    /**
     * Default service cursor escape {@link PointIF} points
     */
    public static final BiFunction<Integer, Integer, PointIF> CURSOR_POSITION = (row, column) -> getPositionCursorPoint("POSITION", "POS", row, column);
    public static final PointIF CURSOR_SAVE = getServiceCursorPoint("SAVE", "SV", 's');
    public static final PointIF CURSOR_RESTORE = getServiceCursorPoint("RESTORE", "RSTR", 'u');
    /**
     * Default decoration escape {@link PointIF} points
     */
    public static final PointIF BOLD = create("BOLD", "BLD", 1, PointType.DECORATION_CONTROL);
    public static final PointIF FAINT = create("FAINT", "FNT", 2, PointType.DECORATION_CONTROL);
    public static final PointIF INVERSE = create("INVERSE", "INV", 3, PointType.DECORATION_CONTROL);
    public static final PointIF UNDERLINE = create("UNDERLINE", "ULN", 4, PointType.DECORATION_CONTROL);
    public static final PointIF SLOW_BLINK = create("SLOW_BLINK", "S_BLK", 5, PointType.DECORATION_CONTROL);
    public static final PointIF FAST_BLINK = create("FAST_BLINK", "F_BLK", 6, PointType.DECORATION_CONTROL);
    public static final PointIF REVERSE = create("REVERSE", "REV", 7, PointType.DECORATION_CONTROL);
    public static final PointIF BLANK = create("BLANK", "BLN", 8, PointType.DECORATION_CONTROL);
    public static final PointIF STRIKE = create("STRIKE", "STRK", 9, PointType.DECORATION_CONTROL);
    //public static final PointIF DOUBLE_UNDERLINE = create("DOUBLE_UNDERLINE", "D_ULN", 21, PointType.DECORATION_CONTROL);
    public static final PointIF FRAMED = create("FRAMED", "FRM", 51, PointType.DECORATION_CONTROL);
    public static final PointIF CIRCLED = create("CIRCLED", "CRC", 52, PointType.DECORATION_CONTROL);
    public static final PointIF OVERLINE = create("OVERLINE", "OLN", 53, PointType.DECORATION_CONTROL);
    /**
     * Default ideogram escape {@link PointIF} points
     */
    public static final PointIF RIGHT_SIDE_LINE = create("RIGHT_SIDE_LINE", "R_SD_LN", 60, PointType.IDEOGRAM);
    public static final PointIF RIGHT_SIDE_DOUBLE_LINE = create("RIGHT_SIDE_DOUBLE_LINE", "R_SD_DLN", 61, PointType.IDEOGRAM);
    public static final PointIF LEFT_SIDE_LINE = create("LEFT_SIDE_LINE", "L_SD_LN", 62, PointType.IDEOGRAM);
    public static final PointIF LEFT_SIDE_DOUBLE_LINE = create("LEFT_SIDE_DOUBLE_LINE", "L_SD_DLN", 63, PointType.IDEOGRAM);
    public static final PointIF STRESS_MARKING = create("STRESS_MARKING", "STRSS", 64, PointType.IDEOGRAM);
    /**
     * Default service escape {@link PointIF} points
     */
    public static final PointIF RESET = create("RESET", "RST", 0, PointType.SERVICE_CONTROL);
    public static final PointIF FRAKTUR = create("FRAKTUR", "FRKTR", 20, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_BOLD = create("RESET_BOLD", "RST_BLD", 21, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_COLOR = create("RESET_COLOR", "RST_CLR", 22, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_ITALIC = create("RESET_ITALIC", "RST_IT", 23, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_UNDERLINE = create("RESET_UNDERLINE", "RST_ULN", 24, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_BLINK = create("RESET_BLINK", "RST_BLNK", 25, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_INVERSE = create("RESET_INVERSE", "RST_INV", 27, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_BLANK = create("RESET_BLANK", "RST_BLNK", 28, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_STRIKE = create("RESET_STRIKE", "RST_STRK", 29, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_FRAMED = create("RESET_FRAMED", "RST_FRM", 54, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_OVERLINE = create("RESET_OVERLINE", "RST_OLN", 55, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_IDEOGRAM = create("RESET_IDEOGRAM", "RST_IDEO", 65, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_FOREGROUND = create("RESET_FOREGROUND", "RST_FG", 39, PointType.SERVICE_CONTROL);
    public static final PointIF RESET_BACKGROUND = create("RESET_BACKGROUND", "RST_BG", 49, PointType.SERVICE_CONTROL);
    /**
     * Default foreground color escape {@link PointIF} points
     */
    public static final PointIF BlACK_FOREGROUND = create("BlACK_FOREGROUND", "BLACK_FG", 30, PointType.FOREGROUND_COLOR);
    public static final PointIF RED_FOREGROUND = create("RED_FOREGROUND", "RED_FG", 31, PointType.FOREGROUND_COLOR);
    public static final PointIF GREEN_FOREGROUND = create("GREEN_FOREGROUND", "GREEN_FG", 32, PointType.FOREGROUND_COLOR);
    public static final PointIF YELLOW_FOREGROUND = create("YELLOW_FOREGROUND", "YELLOW_FG", 33, PointType.FOREGROUND_COLOR);
    public static final PointIF BLUE_FOREGROUND = create("BLUE_FOREGROUND", "BLUE_FG", 34, PointType.FOREGROUND_COLOR);
    public static final PointIF MAGENTA_FOREGROUND = create("MAGENTA_FOREGROUND", "MAGENTA_FG", 35, PointType.FOREGROUND_COLOR);
    public static final PointIF CYAN_FOREGROUND = create("CYAN_FOREGROUND", "CYAN_FG", 36, PointType.FOREGROUND_COLOR);
    public static final PointIF WHITE_FOREGROUND = create("WHITE_FOREGROUND", "WHITE_FG", 37, PointType.FOREGROUND_COLOR);
    /**
     * Default foreground bright color escape {@link PointIF} points
     */
    public static final PointIF BRIGHT_BLACK_FOREGROUND = getBrightColorPoint("BRIGHT_BLACK_FOREGROUND", "BR_BLACK_FG", 30, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_RED_FOREGROUND = getBrightColorPoint("BRIGHT_RED_FOREGROUND", "BR_RED_FG", 31, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_GREEN_FOREGROUND = getBrightColorPoint("BRIGHT_GREEN_FOREGROUND", "BR_GREEN_FG", 32, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_YELLOW_FOREGROUND = getBrightColorPoint("BRIGHT_YELLOW_FOREGROUND", "BR_YELLOW_FG", 33, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_BLUE_FOREGROUND = getBrightColorPoint("BRIGHT_BLUE_FOREGROUND", "BR_BLUE_FG", 34, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_MAGENTA_FOREGROUND = getBrightColorPoint("BRIGHT_MAGENTA_FOREGROUND", "BR_MAGENTA_FG", 35, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_CYAN_FOREGROUND = getBrightColorPoint("BRIGHT_CYAN_FOREGROUND", "BR_CYAN_FG", 36, PointType.FOREGROUND_COLOR);
    public static final PointIF BRIGHT_WHITE_FOREGROUND = getBrightColorPoint("BRIGHT_WHITE_FOREGROUND", "BR_WHITE_FG", 37, PointType.FOREGROUND_COLOR);
    /**
     * Default background color escape {@link PointIF} points
     */
    public static final PointIF BLACK_BACKGROUND = create("BLACK_BACKGROUND", "BLACK_BG", 40, PointType.BACKGROUND_COLOR);
    public static final PointIF RED_BACKGROUND = create("RED_BACKGROUND", "RED_BG", 41, PointType.BACKGROUND_COLOR);
    public static final PointIF GREEN_BACKGROUND = create("GREEN_BACKGROUND", "GREEN_BG", 44, PointType.BACKGROUND_COLOR);
    public static final PointIF YELLOW_BACKGROUND = create("YELLOW_BACKGROUND", "YELLOW_BG", 43, PointType.BACKGROUND_COLOR);
    public static final PointIF BLUE_BACKGROUND = create("BLUE_BACKGROUND", "BLUE_BG", 44, PointType.BACKGROUND_COLOR);
    public static final PointIF MAGENTA_BACKGROUND = create("MAGENTA_BACKGROUND", "MAGENTA_BG", 45, PointType.BACKGROUND_COLOR);
    public static final PointIF CYAN_BACKGROUND = create("CYAN_BACKGROUND", "CYAN_BG", 46, PointType.BACKGROUND_COLOR);
    public static final PointIF WHITE_BACKGROUND = create("WHITE_BACKGROUND", "WHITE_BG", 47, PointType.BACKGROUND_COLOR);
    /**
     * Default background bright color escape {@link PointIF} points
     */
    public static final PointIF BRIGHT_BLACK_BACKGROUND = getBrightColorPoint("BRIGHT_BLACK_BACKGROUND", "BR_BLACK_BG", 40, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_RED_BACKGROUND = getBrightColorPoint("BRIGHT_RED_BACKGROUND", "BR_RED_BG", 41, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_GREEN_BACKGROUND = getBrightColorPoint("BRIGHT_GREEN_BACKGROUND", "BR_GREEN_BG", 44, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_YELLOW_BACKGROUND = getBrightColorPoint("BRIGHT_YELLOW_BACKGROUND", "BR_YELLOW_BG", 43, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_BLUE_BACKGROUND = getBrightColorPoint("BRIGHT_BLUE_BACKGROUND", "BR_BLUE_BG", 44, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_MAGENTA_BACKGROUND = getBrightColorPoint("BRIGHT_MAGENTA_BACKGROUND", "BR_MAGENTA_BG", 45, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_CYAN_BACKGROUND = getBrightColorPoint("BRIGHT_CYAN_BACKGROUND", "BR_CYAN_BG", 46, PointType.BACKGROUND_COLOR);
    public static final PointIF BRIGHT_WHITE_BACKGROUND = getBrightColorPoint("BRIGHT_WHITE_BACKGROUND", "BR_WHITE_BG", 47, PointType.BACKGROUND_COLOR);
    /**
     * Default foreground color map
     */
    public static final PointIF[] FOREGROUND_COLOR_MAP = {
        BlACK_FOREGROUND,
        RED_FOREGROUND,
        GREEN_FOREGROUND,
        YELLOW_FOREGROUND,
        BLUE_FOREGROUND,
        MAGENTA_FOREGROUND,
        CYAN_FOREGROUND,
        WHITE_FOREGROUND
    };
    /**
     * Default foreground bright color map
     */
    public static final PointIF[] FOREGROUND_BRIGHT_COLOR_MAP = {
        BRIGHT_BLACK_FOREGROUND,
        BRIGHT_RED_FOREGROUND,
        BRIGHT_GREEN_FOREGROUND,
        BRIGHT_YELLOW_FOREGROUND,
        BRIGHT_BLUE_FOREGROUND,
        BRIGHT_MAGENTA_FOREGROUND,
        BRIGHT_CYAN_FOREGROUND,
        BRIGHT_WHITE_FOREGROUND
    };
    /**
     * Default background color map
     */
    public static final PointIF[] BACKGROUND_COLOR_MAP = {
        BLACK_BACKGROUND,
        RED_BACKGROUND,
        GREEN_BACKGROUND,
        YELLOW_BACKGROUND,
        BLUE_BACKGROUND,
        MAGENTA_BACKGROUND,
        CYAN_BACKGROUND,
        WHITE_BACKGROUND
    };
    /**
     * Default background bright color map
     */
    public static final PointIF[] BACKGROUND_BRIGHT_COLOR_MAP = {
        BRIGHT_BLACK_BACKGROUND,
        BRIGHT_RED_BACKGROUND,
        BRIGHT_GREEN_BACKGROUND,
        BRIGHT_YELLOW_BACKGROUND,
        BRIGHT_BLUE_BACKGROUND,
        BRIGHT_MAGENTA_BACKGROUND,
        BRIGHT_CYAN_BACKGROUND,
        BRIGHT_WHITE_BACKGROUND
    };

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
    private PointType type;

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code
     * @param type   - initial input type
     * @return new {@link PointIF} instance
     */
    public static PointIF create(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final String code, final PointType type) {
        if (isEmpty(symbol)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return Point.builder()
            .name(name)
            .symbol(symbol)
            .code(code)
            .type(type)
            .build();
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code
     * @param type   - initial input type
     * @return new {@link PointIF} instance
     */
    public static PointIF create(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int code, final PointType type) {
        return create(name, symbol, String.format(DEFAULT_ESCAPE_FORMAT, code), type);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input code {@link CharSequence}
     * @param color  - initial input color {@link Color}
     * @param type   - initial input color type
     * @return new {@link PointIF} instance
     */
    public static PointIF getRGBPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final Color color, final PointType type) {
        return create(name, symbol, String.format(DEFAULT_RGB_COLOR_ESCAPE_FORMAT, color.getRedComponent(), color.getGreenComponent(), color.getBlueComponent()), type);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link PointIF} instance
     */
    public static PointIF getForegroundColor(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final CharSequence code) {
        return create(name, symbol, String.format(DEFAULT_FOREGROUND_COLOR_ESCAPE_FORMAT, code), PointType.FOREGROUND_COLOR);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link PointIF} instance
     */
    public static PointIF getBackgroundColor(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final CharSequence code) {
        return create(name, symbol, String.format(DEFAULT_BACKGROUND_COLOR_ESCAPE_FORMAT, code), PointType.BACKGROUND_COLOR);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link PointIF} instance
     */
    private static PointIF getBrightColorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int code, final PointType type) {
        return create(name, symbol, String.format(DEFAULT_BRIGHT_COLOR_ESCAPE_FORMAT, code), type);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name     - initial input symbol name {@link CharSequence}
     * @param symbol   - initial input symbol {@link CharSequence}
     * @param position - initial input cursor position
     * @param type     - initial input cursor type
     * @return new {@link PointIF} instance
     */
    public static PointIF getCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int position, final CursorPositionType type) {
        return create(name, symbol, String.format(DEFAULT_CURSOR_ESCAPE_FORMAT, position, type), PointType.CURSOR_CONTROL);
    }

    /**
     * Returns new {@link PointIF} instance by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param row    - initial input row position
     * @param column - initial input column position
     * @return new {@link PointIF} instance
     */
    public static PointIF getPositionCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final int row, final int column) {
        return create(name, symbol, String.format(DEFAULT_CURSOR_POSITION_ESCAPE_FORMAT, row, column, CursorPositionType.H), PointType.CURSOR_CONTROL);
    }

    /**
     * Returns new {@link PointIF} by input data parameters
     *
     * @param name   - initial input symbol name {@link CharSequence}
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input cursor service code
     * @return new {@link PointIF} instance
     */
    public static PointIF getServiceCursorPoint(@NonNull final CharSequence name, @NonNull final CharSequence symbol, final char code) {
        return create(name, symbol, String.format(DEFAULT_CURSOR_SERVICE_ESCAPE_FORMAT, code), PointType.CURSOR_CONTROL);
    }
}
