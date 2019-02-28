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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import lombok.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Default point implementation {@link Point}
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
     * Default character escape format
     */
    private static final String DEFAULT_ESCAPE_FORMAT = ((char) 27) + "[%sm";

    /**
     * Default service escape points
     */
    public static final Point RESET = DefaultPoint.getPoint("reset", "0", Type.SERVICE_CONTROL);
    public static final Point BOLD = DefaultPoint.getPoint("bold", "1", Type.DECORATION_CONTROL);
    public static final Point DIM = DefaultPoint.getPoint("dim", "2", Type.DECORATION_CONTROL);
    public static final Point UNDER_LINE = DefaultPoint.getPoint("uline", "4", Type.DECORATION_CONTROL);
    public static final Point BLINK = DefaultPoint.getPoint("blink", "5", Type.DECORATION_CONTROL);
    public static final Point REVERSE = DefaultPoint.getPoint("rev", "7", Type.DECORATION_CONTROL);
    public static final Point BLANK = DefaultPoint.getPoint("blank", "8", Type.DECORATION_CONTROL);
    public static final Point OVERSTRIKE = DefaultPoint.getPoint("ostrike", "9", Type.DECORATION_CONTROL);

    /**
     * Default foreground color escape points
     */
    public static final Point BlACK_FOREGROUND = DefaultPoint.getPoint("black_fg", "30", Type.FOREGROUND_COLOR);
    public static final Point RED_FOREGROUND = DefaultPoint.getPoint("red_fg", "31", Type.FOREGROUND_COLOR);
    public static final Point GREEN_FOREGROUND = DefaultPoint.getPoint("green_fg", "32", Type.FOREGROUND_COLOR);
    public static final Point YELLOW_FOREGROUND = DefaultPoint.getPoint("yellow_fg", "33", Type.FOREGROUND_COLOR);
    public static final Point BLUE_FOREGROUND = DefaultPoint.getPoint("blue_fg", "34", Type.FOREGROUND_COLOR);
    public static final Point MAGENTA_FOREGROUND = DefaultPoint.getPoint("magenta_fg", "35", Type.FOREGROUND_COLOR);
    public static final Point CYAN_FOREGROUND = DefaultPoint.getPoint("cyan_fg", "36", Type.FOREGROUND_COLOR);
    public static final Point WHITE_FOREGROUND = DefaultPoint.getPoint("white_fg", "37", Type.FOREGROUND_COLOR);

    /**
     * Default background color escape points
     */
    public static final Point BLACK_BACKGROUND = DefaultPoint.getPoint("black_bg", "40", Type.BACKGROUND_COLOR);
    public static final Point RED_BACKGROUND = DefaultPoint.getPoint("red_bg", "41", Type.BACKGROUND_COLOR);
    public static final Point GREEN_BACKGROUND = DefaultPoint.getPoint("green_bg", "44", Type.BACKGROUND_COLOR);
    public static final Point YELLOW_BACKGROUND = DefaultPoint.getPoint("yellow_bg", "43", Type.BACKGROUND_COLOR);
    public static final Point BLUE_BACKGROUND = DefaultPoint.getPoint("blue_bg", "44", Type.BACKGROUND_COLOR);
    public static final Point MAGENTA_BACKGROUND = DefaultPoint.getPoint("magenta_bg", "45", Type.BACKGROUND_COLOR);
    public static final Point CYAN_BACKGROUND = DefaultPoint.getPoint("cyan_bg", "46", Type.BACKGROUND_COLOR);
    public static final Point WHITE_BACKGROUND = DefaultPoint.getPoint("white_bg", "47", Type.BACKGROUND_COLOR);

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
     * Returns new {@link DefaultPoint} instance by input data parameters
     *
     * @param symbol - initial input symbol {@link CharSequence}
     * @param code   - initial input code {@link CharSequence}
     * @return new {@link DefaultPoint} instance
     */
    public static DefaultPoint getPoint(@NonNull final CharSequence symbol, final CharSequence code, final Type type) {
        if (isEmpty(code)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape symbol={%s}, should not be empty or null", symbol));
        }
        return DefaultPoint.builder()
            .code(code)
            .symbol(String.format(DEFAULT_ESCAPE_FORMAT, symbol))
            .type(type)
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
