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

import com.wildbeeslabs.sensiblemetrics.ansifancy.exception.StyleException;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.PointIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.StyleIF;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default style implementation {@link StyleIF}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Style implements StyleIF {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -7712589716010883744L;

    /**
     * Default control styles
     */
    public static final StyleIF RESET = Style.getStyle("reset", "reset", Point.RESET);
    public static final StyleIF BOLD = Style.getStyle("bold", "bold style", Point.BOLD);
    public static final StyleIF DIM = Style.getStyle("dim", "dim style", Point.FAINT);
    public static final StyleIF UNDER_LINE = Style.getStyle("uline", "underline style", Point.UNDERLINE);
    public static final StyleIF BLINK = Style.getStyle("blink", "blink style", Point.SLOW_BLINK);
    public static final StyleIF REVERSE = Style.getStyle("rev", "reverse style", Point.REVERSE);
    public static final StyleIF BLANK = Style.getStyle("blank", "blank style", Point.BLANK);
    public static final StyleIF OVER_STRIKE = Style.getStyle("ostrike", "overstrike style", Point.OVERSTRIKE);

    /**
     * Default color styles
     */
    public static final StyleIF BlACK_FOREGROUND = Style.getStyle("black_fg", "black foreground style", Point.BlACK_FOREGROUND);
    public static final StyleIF BLACK_BACKGROUND = Style.getStyle("black_bg", "black background style", Point.BLACK_BACKGROUND);
    public static final StyleIF RED_FOREGROUND = Style.getStyle("red_fg", "red foreground style", Point.RED_FOREGROUND);
    public static final StyleIF RED_BACKGROUND = Style.getStyle("red_bg", "red background style", Point.RED_BACKGROUND);
    public static final StyleIF GREEN_FOREGROUND = Style.getStyle("green_fg", "green foreground style", Point.GREEN_FOREGROUND);
    public static final StyleIF GREEN_BACKGROUND = Style.getStyle("green_bg", "green background style", Point.GREEN_BACKGROUND);
    public static final StyleIF YELLOW_FOREGROUND = Style.getStyle("yellow_fg", "yellow foreground style", Point.YELLOW_FOREGROUND);
    public static final StyleIF YELLOW_BACKGROUND = Style.getStyle("yellow_bg", "yellow background style", Point.YELLOW_BACKGROUND);
    public static final StyleIF BLUE_FOREGROUND = Style.getStyle("blue_fg", "blue foreground style", Point.BLUE_FOREGROUND);
    public static final StyleIF BLUE_BACKGROUND = Style.getStyle("blue_bg", "blue background style", Point.BLUE_BACKGROUND);
    public static final StyleIF MAGENTA_FOREGROUND = Style.getStyle("magenta_fg", "magenta foreground style", Point.MAGENTA_FOREGROUND);
    public static final StyleIF MAGENTA_BACKGROUND = Style.getStyle("magenta_bg", "magenta background style", Point.MAGENTA_BACKGROUND);
    public static final StyleIF CYAN_FOREGROUND = Style.getStyle("cyan_fg", "cyan foreground style", Point.CYAN_FOREGROUND);
    public static final StyleIF CYAN_BACKGROUND = Style.getStyle("cyan_bg", "cyan background style", Point.CYAN_BACKGROUND);
    public static final StyleIF WHITE_FOREGROUND = Style.getStyle("white_fg", "white foreground style", Point.WHITE_FOREGROUND);
    public static final StyleIF WHITE_BACKGROUND = Style.getStyle("white_bg", "white background style", Point.WHITE_BACKGROUND);

    /**
     * Default style title
     */
    private String title;

    /**
     * Default style content
     */
    private String description;

    /**
     * Default collection of style points
     */
    private Collection<PointIF> points;

    /**
     * Returns current {@link Style} style updated by input array of styles {@link StyleIF}
     *
     * @param points - initial input array of styles {@link PointIF}
     * @return updated {@link Style} style
     */
    public Style add(final PointIF... points) {
        Arrays.asList(Optional.ofNullable(points).orElseGet(() -> new PointIF[0])).stream().forEach(getPoints()::add);
        return this;
    }

    /**
     * Returns current {@link Style} updated by {@link PointIF} instance
     *
     * @param name   - initial input  data view {@link CharSequence}
     * @param symbol - initial input  data view {@link CharSequence}
     * @param code   - initial input data code {@link CharSequence}
     * @return updated {@link Point} instance
     */
    public Style add(final CharSequence name, final CharSequence symbol, final int code, final PointIF.PointType type) {
        getPoints().add(Point.getPoint(name, symbol, code, type));
        return this;
    }

    /**
     * Returns current {@link Style} updated by input {@link Style} instance
     *
     * @param style - initial input {@link Style} instance
     * @return updated {@link Point} instance
     */
    public Style inherit(final Style style) {
        if (Objects.isNull(style)) {
            throw StyleException.invalidStyle();
        }
        getPoints().addAll(style.getPoints());
        return this;
    }

    /**
     * Returns string representation of current collection of {@link PointIF} data codes
     *
     * @return string representation of current collection of {@link PointIF} data codes
     */
    public String getCodePoints() {
        return getPoints().stream()
            .map(PointIF::getCode)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns string representation of current collection of {@link PointIF} data symbols
     *
     * @return string representation of current collection of {@link PointIF} data symbols
     */
    public String getSymbolPoints() {
        return getPoints().stream()
            .map(PointIF::getSymbol)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns new {@link Style} instance by input parameters
     *
     * @param title       - initial input style title {@link String}
     * @param description - initial input style description {@link String}
     * @param points      - initial input array of style points {@link PointIF}
     * @return new {@link Style} instance
     */
    public static Style getStyle(final String title, final String description, final PointIF... points) {
        return Style.builder()
            .title(title)
            .description(description)
            .points(Arrays.asList(Optional.ofNullable(points).orElse(new PointIF[0])))
            .build();
    }
}
