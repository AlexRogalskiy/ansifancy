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
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Style;
import lombok.*;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Default style implementation {@link Style}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class DefaultStyle implements Style {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -7712589716010883744L;

    /**
     * Default control styles
     */
    public static final Style RESET = DefaultStyle.getStyle("reset", "reset", DefaultPoint.RESET);
    public static final Style BOLD = DefaultStyle.getStyle("bold", "bold style", DefaultPoint.BOLD);
    public static final Style DIM = DefaultStyle.getStyle("dim", "dim style", DefaultPoint.DIM);
    public static final Style UNDER_LINE = DefaultStyle.getStyle("uline", "underline style", DefaultPoint.UNDER_LINE);
    public static final Style BLINK = DefaultStyle.getStyle("blink", "blink style", DefaultPoint.BLINK);
    public static final Style REVERSE = DefaultStyle.getStyle("rev", "reverse style", DefaultPoint.REVERSE);
    public static final Style BLANK = DefaultStyle.getStyle("blank", "blank style", DefaultPoint.BLANK);
    public static final Style OVER_STRIKE = DefaultStyle.getStyle("ostrike", "overstrike style", DefaultPoint.OVERSTRIKE);

    /**
     * Default color styles
     */
    public static final Style BlACK_FOREGROUND = DefaultStyle.getStyle("black_fg", "black foreground style", DefaultPoint.BlACK_FOREGROUND);
    public static final Style BLACK_BACKGROUND = DefaultStyle.getStyle("black_bg", "black background style", DefaultPoint.BLACK_BACKGROUND);
    public static final Style RED_FOREGROUND = DefaultStyle.getStyle("red_fg", "red foreground style", DefaultPoint.RED_FOREGROUND);
    public static final Style RED_BACKGROUND = DefaultStyle.getStyle("red_bg", "red background style", DefaultPoint.RED_BACKGROUND);
    public static final Style GREEN_FOREGROUND = DefaultStyle.getStyle("green_fg", "green foreground style", DefaultPoint.GREEN_FOREGROUND);
    public static final Style GREEN_BACKGROUND = DefaultStyle.getStyle("green_bg", "green background style", DefaultPoint.GREEN_BACKGROUND);
    public static final Style YELLOW_FOREGROUND = DefaultStyle.getStyle("yellow_fg", "yellow foreground style", DefaultPoint.YELLOW_FOREGROUND);
    public static final Style YELLOW_BACKGROUND = DefaultStyle.getStyle("yellow_bg", "yellow background style", DefaultPoint.YELLOW_BACKGROUND);
    public static final Style BLUE_FOREGROUND = DefaultStyle.getStyle("blue_fg", "blue foreground style", DefaultPoint.BLUE_FOREGROUND);
    public static final Style BLUE_BACKGROUND = DefaultStyle.getStyle("blue_bg", "blue background style", DefaultPoint.BLUE_BACKGROUND);
    public static final Style MAGENTA_FOREGROUND = DefaultStyle.getStyle("magenta_fg", "magenta foreground style", DefaultPoint.MAGENTA_FOREGROUND);
    public static final Style MAGENTA_BACKGROUND = DefaultStyle.getStyle("magenta_bg", "magenta background style", DefaultPoint.MAGENTA_BACKGROUND);
    public static final Style CYAN_FOREGROUND = DefaultStyle.getStyle("cyan_fg", "cyan foreground style", DefaultPoint.CYAN_FOREGROUND);
    public static final Style CYAN_BACKGROUND = DefaultStyle.getStyle("cyan_bg", "cyan background style", DefaultPoint.CYAN_BACKGROUND);
    public static final Style WHITE_FOREGROUND = DefaultStyle.getStyle("white_fg", "white foreground style", DefaultPoint.WHITE_FOREGROUND);
    public static final Style WHITE_BACKGROUND = DefaultStyle.getStyle("white_bg", "white background style", DefaultPoint.WHITE_BACKGROUND);

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
    private Collection<Point> points;

    /**
     * Returns current {@link DefaultStyle} style updated by input array of styles {@link Style}
     *
     * @param points - initial input array of styles {@link Point}
     * @return updated {@link DefaultStyle} style
     */
    public DefaultStyle add(final Point... points) {
        Arrays.asList(Optional.ofNullable(points).orElseThrow(StyleException::invalidPoint)).stream().forEach(getPoints()::add);
        return this;
    }

    /**
     * Returns current {@link DefaultStyle} updated by {@link Point} instance
     *
     * @param dataView - initial input  data view {@link CharSequence}
     * @param dataCode - initial input data code {@link CharSequence}
     * @return updated {@link DefaultPoint} instance
     */
    public DefaultStyle add(final CharSequence dataView, final CharSequence dataCode, final Point.Type type) {
        getPoints().add(DefaultPoint.getPoint(dataView, dataCode, type));
        return this;
    }

    /**
     * Returns current {@link DefaultStyle} updated by input {@link DefaultStyle} instance
     *
     * @param style - initial input {@link DefaultStyle} instance
     * @return updated {@link DefaultPoint} instance
     */
    public DefaultStyle inherit(final DefaultStyle style) {
        if (Objects.isNull(style)) {
            throw StyleException.invalidStyle();
        }
        getPoints().addAll(style.getPoints());
        return this;
    }

    /**
     * Returns string representation of current collection of {@link Point} data codes
     *
     * @return string representation of current collection of {@link Point} data codes
     */
    public String getCodePoints() {
        return getPoints().stream()
            .map(Point::getCode)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns string representation of current collection of {@link Point} data symbols
     *
     * @return string representation of current collection of {@link Point} data symbols
     */
    public String getSymbolPoints() {
        return getPoints().stream()
            .map(Point::getSymbol)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns new {@link DefaultStyle} instance by input parameters
     *
     * @param title       - initial input style title {@link String}
     * @param description - initial input style description {@link String}
     * @param points      - initial input array of style points {@link Point}
     * @return new {@link DefaultStyle} instance
     */
    public static DefaultStyle getStyle(final String title, final String description, final Point... points) {
        return DefaultStyle.builder()
            .title(title)
            .description(description)
            .points(Arrays.asList(Optional.ofNullable(points).orElse(new Point[0])))
            .build();
    }
}
