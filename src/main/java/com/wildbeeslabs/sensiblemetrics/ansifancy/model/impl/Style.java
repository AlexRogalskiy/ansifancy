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
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.PointIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface.StyleIF;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Default {@link StyleIF} implementation
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
     * Default control {@link StyleIF}
     */
    public static final StyleIF RESET = Style.create("reset", "reset", Point.RESET);
    public static final StyleIF BOLD = Style.create("bold", "bold style", Point.BOLD);
    public static final StyleIF DIM = Style.create("dim", "dim style", Point.FAINT);
    public static final StyleIF UNDER_LINE = Style.create("uline", "underline style", Point.UNDERLINE);
    public static final StyleIF BLINK = Style.create("blink", "blink style", Point.SLOW_BLINK);
    public static final StyleIF REVERSE = Style.create("rev", "reverse style", Point.REVERSE);
    public static final StyleIF BLANK = Style.create("blank", "blank style", Point.BLANK);
    public static final StyleIF OVER_STRIKE = Style.create("ostrike", "overstrike style", Point.STRIKE);

    /**
     * Default color {@link StyleIF}
     */
    public static final StyleIF BlACK_FOREGROUND = Style.create("black_fg", "black foreground style", Point.BlACK_FOREGROUND);
    public static final StyleIF BLACK_BACKGROUND = Style.create("black_bg", "black background style", Point.BLACK_BACKGROUND);
    public static final StyleIF RED_FOREGROUND = Style.create("red_fg", "red foreground style", Point.RED_FOREGROUND);
    public static final StyleIF RED_BACKGROUND = Style.create("red_bg", "red background style", Point.RED_BACKGROUND);
    public static final StyleIF GREEN_FOREGROUND = Style.create("green_fg", "green foreground style", Point.GREEN_FOREGROUND);
    public static final StyleIF GREEN_BACKGROUND = Style.create("green_bg", "green background style", Point.GREEN_BACKGROUND);
    public static final StyleIF YELLOW_FOREGROUND = Style.create("yellow_fg", "yellow foreground style", Point.YELLOW_FOREGROUND);
    public static final StyleIF YELLOW_BACKGROUND = Style.create("yellow_bg", "yellow background style", Point.YELLOW_BACKGROUND);
    public static final StyleIF BLUE_FOREGROUND = Style.create("blue_fg", "blue foreground style", Point.BLUE_FOREGROUND);
    public static final StyleIF BLUE_BACKGROUND = Style.create("blue_bg", "blue background style", Point.BLUE_BACKGROUND);
    public static final StyleIF MAGENTA_FOREGROUND = Style.create("magenta_fg", "magenta foreground style", Point.MAGENTA_FOREGROUND);
    public static final StyleIF MAGENTA_BACKGROUND = Style.create("magenta_bg", "magenta background style", Point.MAGENTA_BACKGROUND);
    public static final StyleIF CYAN_FOREGROUND = Style.create("cyan_fg", "cyan foreground style", Point.CYAN_FOREGROUND);
    public static final StyleIF CYAN_BACKGROUND = Style.create("cyan_bg", "cyan background style", Point.CYAN_BACKGROUND);
    public static final StyleIF WHITE_FOREGROUND = Style.create("white_fg", "white foreground style", Point.WHITE_FOREGROUND);
    public static final StyleIF WHITE_BACKGROUND = Style.create("white_bg", "white background style", Point.WHITE_BACKGROUND);

    /**
     * Style title
     */
    private String title;

    /**
     * Style content
     */
    private String description;

    /**
     * {@link Collection} of style points
     */
    private Collection<PointIF> points;

    /**
     * {@link Map} collection of {@link PointIF}
     */
    private Map<CharSequence, PointIF> pointMap;

    /**
     * Returns current {@link StyleIF} updated by input collection of style {@link PointIF}
     *
     * @param points - initial input collection of style {@link PointIF} to be added
     * @return updated {@link StyleIF}
     */
    public StyleIF addPoints(final PointIF... points) {
        Arrays.asList(Optional.ofNullable(points).orElseGet(() -> new PointIF[0])).forEach(this::addPoint);
        return this;
    }

    /**
     * Sets current {@link StyleIF} by input {@link Iterable} collection of {@link PointIF}
     *
     * @param points - initial input {@link Iterable} collection of {@link PointIF}
     */
    public void setPoints(final Iterable<? extends PointIF> points) {
        this.getPoints().clear();
        this.getPointMap().clear();
        Optional.ofNullable(points)
            .orElseGet(Collections::emptyList)
            .forEach(this::addPoint);
    }

    /**
     * Returns current {@link StyleIF} updated by input style point {@link PointIF}
     *
     * @param point - initial input style {@link PointIF} to update by
     * @return updated {@link StyleIF}
     */
    public StyleIF addPoint(final PointIF point) {
        if (Objects.nonNull(point)) {
            this.getPoints().add(point);
            this.getPointMap().put(point.getCode(), point);
        }
        return this;
    }

    /**
     * Returns current {@link StyleIF} updated by style point parameters
     *
     * @param name   - initial input point name {@link CharSequence}
     * @param symbol - initial input point symbol {@link CharSequence}
     * @param code   - initial input point code {@link CharSequence}
     * @return updated {@link StyleIF}
     */
    public StyleIF add(final CharSequence name, final CharSequence symbol, final int code, final PointIF.PointType type) {
        this.getPoints().add(Point.create(name, symbol, code, type));
        return this;
    }

    /**
     * Returns current {@link StyleIF} updated by input {@link StyleIF} instance
     *
     * @param style - initial input {@link StyleIF} to update by
     * @return updated {@link StyleIF} instance
     */
    public StyleIF add(final StyleIF style) {
        if (Objects.isNull(style)) {
            throw StyleException.invalidStyle();
        }
        this.addPoints(style.getPoints());
        return this;
    }

    /**
     * Returns string representation of current collection of {@link PointIF} data codes
     *
     * @return string representation of current collection of {@link PointIF} data codes
     */
    public String getCodePoints() {
        return this.getPoints().stream()
            .map(PointIF::getCode)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns {@link String} representation of current collection of {@link PointIF} symbols
     *
     * @return {@link String} representation of current collection of {@link PointIF} symbols
     */
    public String getSymbolPoints() {
        return this.getPoints().stream()
            .map(PointIF::getSymbol)
            .map(String::valueOf)
            .collect(Collectors.joining(StringUtils.EMPTY));
    }

    /**
     * Returns new {@link StyleIF} instance by input parameters
     *
     * @param title       - initial input style title {@link String}
     * @param description - initial input style description {@link String}
     * @param points      - initial input array of style points {@link PointIF}
     * @return new {@link StyleIF} instance
     */
    public static StyleIF create(final String title, final String description, final PointIF... points) {
        final Style style = Style.builder()
            .title(title)
            .description(description)
            .build();
        style.addPoints(points);
        return style;
    }

    /**
     * Returns binary flag if input {@link PointIF} exists in current collection of style points
     *
     * @param value - initial input {@link PointIF} to check by
     * @return true - if input point exists, false - otherwise
     */
    public boolean contains(final PointIF value) {
        return this.getPointMap().containsKey(value.getCode());
    }

    /**
     * Returns collection size of style points {@link PointIF}
     *
     * @return collection size of style points {@link PointIF}
     */
    public int size() {
        return this.getPoints().size();
    }

    /**
     * Returns {@link PointIF} instance by input point symbol
     *
     * @param key - initial input point symbol to check by
     * @return {@link PointIF} instance
     */
    public PointIF getPoint(final CharSequence key) {
        return this.getPointMap().get(key);
    }

    /**
     * Removes {@link PointIF} from current collection of style points
     *
     * @param point - initial input {@link PointIF} to remove
     */
    public void removePoint(final PointIF point) {
        if (Objects.nonNull(point)) {
            this.getPoints().remove(point);
            this.getPointMap().remove(point.getCode());
        }
    }

    /**
     * Returns current {@link StyleIF} updated by input collection of style {@link PointIF}
     *
     * @param points - initial input collection of style {@link PointIF} to be removed
     * @return updated {@link StyleIF} style
     */
    public StyleIF removePoints(final PointIF... points) {
        Arrays.asList(Optional.ofNullable(points).orElseGet(() -> new PointIF[0])).forEach(this::removePoint);
        return this;
    }
}
