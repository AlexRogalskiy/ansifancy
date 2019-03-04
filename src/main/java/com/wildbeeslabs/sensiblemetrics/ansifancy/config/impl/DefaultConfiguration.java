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
package com.wildbeeslabs.sensiblemetrics.ansifancy.config.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.Configuration;
import com.wildbeeslabs.sensiblemetrics.ansifancy.exception.ConfigurationException;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MarkerSequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.Marker;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.MetaData;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.Style;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Default configuration implementation
 */
@Data
@EqualsAndHashCode
@ToString
public class DefaultConfiguration implements Configuration {

    /**
     * Default marker map {@link Map}
     */
    private final Map<String, MarkerSequence> DEFAULT_MARKER_MAP = new HashMap<>();

    /**
     * Default configuration constructor
     */
    public DefaultConfiguration() {
        initialize();
    }

    /**
     * Initializes default marker map
     */
    private void initialize() {
        /**
         * Default control styles
         */
        DEFAULT_MARKER_MAP.put("reset", Marker.builder().metaData(MetaData.getMetaData("reset", "reset")).styles(asList(Style.RESET)).build());
        DEFAULT_MARKER_MAP.put("bold", Marker.builder().metaData(MetaData.getMetaData("bold", "bold letter")).styles(asList(Style.BOLD)).build());
        DEFAULT_MARKER_MAP.put("dim", Marker.builder().metaData(MetaData.getMetaData("dim", "dim letter")).styles(asList(Style.DIM)).build());
        DEFAULT_MARKER_MAP.put("uline", Marker.builder().metaData(MetaData.getMetaData("uline", "underline letter")).styles(asList(Style.UNDER_LINE)).build());
        DEFAULT_MARKER_MAP.put("blink", Marker.builder().metaData(MetaData.getMetaData("blink", "blink letter")).styles(asList(Style.BLINK)).build());
        DEFAULT_MARKER_MAP.put("rev", Marker.builder().metaData(MetaData.getMetaData("rev", "reverse letter")).styles(asList(Style.REVERSE)).build());
        DEFAULT_MARKER_MAP.put("blank", Marker.builder().metaData(MetaData.getMetaData("blank", "blank letter")).styles(asList(Style.BLANK)).build());
        DEFAULT_MARKER_MAP.put("ostrike", Marker.builder().metaData(MetaData.getMetaData("ostrike", "overstrike letter")).styles(asList(Style.OVER_STRIKE)).build());
        /**
         * Default color styles
         */
        DEFAULT_MARKER_MAP.put("black_fg", Marker.builder().metaData(MetaData.getMetaData("black_fg", "black foreground letter")).styles(asList(Style.BlACK_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("black_bg", Marker.builder().metaData(MetaData.getMetaData("black_bg", "black background letter")).styles(asList(Style.BLACK_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("red_fg", Marker.builder().metaData(MetaData.getMetaData("red_fg", "red foreground letter")).styles(asList(Style.RED_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("red_bg", Marker.builder().metaData(MetaData.getMetaData("red_bg", "red background letter")).styles(asList(Style.RED_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("green_fg", Marker.builder().metaData(MetaData.getMetaData("green_fg", "green foreground letter")).styles(asList(Style.GREEN_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("green_bg", Marker.builder().metaData(MetaData.getMetaData("green_bg", "green background letter")).styles(asList(Style.GREEN_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("yellow_fg", Marker.builder().metaData(MetaData.getMetaData("yellow_fg", "yellow foreground letter")).styles(asList(Style.YELLOW_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("yellow_bg", Marker.builder().metaData(MetaData.getMetaData("yellow_bg", "yellow background letter")).styles(asList(Style.YELLOW_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("blue_fg", Marker.builder().metaData(MetaData.getMetaData("blue_fg", "blue foreground letter")).styles(asList(Style.BLUE_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("blue_bg", Marker.builder().metaData(MetaData.getMetaData("blue_bg", "blue background letter")).styles(asList(Style.BLUE_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("magenta_fg", Marker.builder().metaData(MetaData.getMetaData("magenta_fg", "magenta foreground letter")).styles(asList(Style.MAGENTA_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("magenta_bg", Marker.builder().metaData(MetaData.getMetaData("magenta_bg", "magenta background letter")).styles(asList(Style.MAGENTA_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("cyan_fg", Marker.builder().metaData(MetaData.getMetaData("cyan_fg", "cyan foreground letter")).styles(asList(Style.CYAN_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("cyan_bg", Marker.builder().metaData(MetaData.getMetaData("cyan_bg", "cyan background letter")).styles(asList(Style.CYAN_BACKGROUND)).build());
        DEFAULT_MARKER_MAP.put("white_fg", Marker.builder().metaData(MetaData.getMetaData("white_fg", "white foreground letter")).styles(asList(Style.WHITE_FOREGROUND)).build());
        DEFAULT_MARKER_MAP.put("white_bg", Marker.builder().metaData(MetaData.getMetaData("white_bg", "white background letter")).styles(asList(Style.WHITE_BACKGROUND)).build());
    }

    /**
     * Returns collection of {@link MarkerSequence} names
     *
     * @return collection of {@link MarkerSequence} names
     */
    public Set<String> getMarkerNames() {
        return Collections.unmodifiableSet(DEFAULT_MARKER_MAP.keySet());
    }

    /**
     * Returns updated {@link DefaultConfiguration} instance by input marker {@link MarkerSequence}
     *
     * @param marker - initial input {@link MarkerSequence} marker to persist
     * @return updated {@link DefaultConfiguration} instance
     */
    public DefaultConfiguration add(final MarkerSequence marker) {
        if (DEFAULT_MARKER_MAP.containsKey(marker.getMetaData().getName())) {
            throw ConfigurationException.alreadyDefined(marker.getMetaData());
        }
        DEFAULT_MARKER_MAP.put(marker.getMetaData().getName(), marker);
        return this;
    }

    /**
     * Returns {@link MarkerSequence} by input name {@link String}
     *
     * @param name - initial input {@link MarkerSequence} name {@link String}
     * @return {@link MarkerSequence} by name
     */
    public MarkerSequence get(final String name) {
        return DEFAULT_MARKER_MAP.get(name);
    }
}
