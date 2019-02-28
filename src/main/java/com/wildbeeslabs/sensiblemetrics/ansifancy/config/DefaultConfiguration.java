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
package com.wildbeeslabs.sensiblemetrics.ansifancy.config;

import com.wildbeeslabs.sensiblemetrics.ansifancy.exception.ConfigurationException;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Sequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.DefaultMetaData;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.DefaultSequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.impl.DefaultStyle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Default configuration
 */
@Data
@EqualsAndHashCode
@ToString
public class DefaultConfiguration {

    /**
     * Default symbols map {@link Map}
     */
    private final Map<String, Sequence> DEFAULT_SEQUENCE_MAP = new HashMap<>();

    /**
     * Default configuration constructor
     */
    public DefaultConfiguration() {
        initialize();
    }

    /**
     * Initializes default sequence map
     */
    private void initialize() {
        /**
         * Default control sequences
         */
        DEFAULT_SEQUENCE_MAP.put("reset", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("reset", "reset")).styles(asList(DefaultStyle.RESET)).build());
        DEFAULT_SEQUENCE_MAP.put("bold", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("bold", "bold letter")).styles(asList(DefaultStyle.BOLD)).build());
        DEFAULT_SEQUENCE_MAP.put("dim", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("dim", "dim letter")).styles(asList(DefaultStyle.DIM)).build());
        DEFAULT_SEQUENCE_MAP.put("uline", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("uline", "underline letter")).styles(asList(DefaultStyle.UNDER_LINE)).build());
        DEFAULT_SEQUENCE_MAP.put("blink", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("blink", "blink letter")).styles(asList(DefaultStyle.BLINK)).build());
        DEFAULT_SEQUENCE_MAP.put("rev", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("rev", "reverse letter")).styles(asList(DefaultStyle.REVERSE)).build());
        DEFAULT_SEQUENCE_MAP.put("blank", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("blank", "blank letter")).styles(asList(DefaultStyle.BLANK)).build());
        DEFAULT_SEQUENCE_MAP.put("ostrike", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("ostrike", "overstrike letter")).styles(asList(DefaultStyle.OVER_STRIKE)).build());
        /**
         * Default color sequences
         */
        DEFAULT_SEQUENCE_MAP.put("black_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("black_fg", "black foreground letter")).styles(asList(DefaultStyle.BlACK_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("black_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("black_bg", "black background letter")).styles(asList(DefaultStyle.BLACK_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("red_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("red_fg", "red foreground letter")).styles(asList(DefaultStyle.RED_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("red_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("red_bg", "red background letter")).styles(asList(DefaultStyle.RED_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("green_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("green_fg", "green foreground letter")).styles(asList(DefaultStyle.GREEN_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("green_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("green_bg", "green background letter")).styles(asList(DefaultStyle.GREEN_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("yellow_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("yellow_fg", "yellow foreground letter")).styles(asList(DefaultStyle.YELLOW_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("yellow_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("yellow_bg", "yellow background letter")).styles(asList(DefaultStyle.YELLOW_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("blue_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("blue_fg", "blue foreground letter")).styles(asList(DefaultStyle.BLUE_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("blue_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("blue_bg", "blue background letter")).styles(asList(DefaultStyle.BLUE_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("magenta_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("magenta_fg", "magenta foreground letter")).styles(asList(DefaultStyle.MAGENTA_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("magenta_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("magenta_bg", "magenta background letter")).styles(asList(DefaultStyle.MAGENTA_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("cyan_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("cyan_fg", "cyan foreground letter")).styles(asList(DefaultStyle.CYAN_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("cyan_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("cyan_bg", "cyan background letter")).styles(asList(DefaultStyle.CYAN_BACKGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("white_fg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("white_fg", "white foreground letter")).styles(asList(DefaultStyle.WHITE_FOREGROUND)).build());
        DEFAULT_SEQUENCE_MAP.put("white_bg", DefaultSequence.builder().metaData(DefaultMetaData.getMetaData("white_bg", "white background letter")).styles(asList(DefaultStyle.WHITE_BACKGROUND)).build());
    }

    /**
     * Returns collection of {@link CharSequence}
     *
     * @return collection of {@link CharSequence}
     */
    public Set<String> getSequenceNames() {
        return Collections.unmodifiableSet(DEFAULT_SEQUENCE_MAP.keySet());
    }

    /**
     * Returns {@link DefaultConfiguration} instance enriched by initial input {@link Sequence}
     *
     * @param sequence - initial input sequence to be persisted {@link Sequence}
     * @return {@link DefaultConfiguration} instance
     */
    public DefaultConfiguration add(final DefaultSequence sequence) {
        if (DEFAULT_SEQUENCE_MAP.containsKey(sequence.getMetaData().getName())) {
            throw ConfigurationException.alreadyDefined(sequence.getMetaData());
        }
        DEFAULT_SEQUENCE_MAP.put(sequence.getMetaData().getName(), sequence);
        return this;
    }

    /**
     * Returns {@link Sequence} by initial name {@link String}
     *
     * @param sequenceName - initial input sequence name {@link String}
     * @return {@link Sequence} by initial name
     */
    public Sequence get(final String sequenceName) {
        return DEFAULT_SEQUENCE_MAP.get(sequenceName);
    }
}
