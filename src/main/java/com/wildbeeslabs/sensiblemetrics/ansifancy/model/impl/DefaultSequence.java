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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MetaData;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Sequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Style;
import lombok.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

/**
 * Default symbol implementation {@link Sequence}
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Builder
@NoArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class DefaultSequence implements Sequence {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = -8851145060884769651L;

    /**
     * Default symbol meta data
     */
    private MetaData metaData;
    /**
     * Default collection of styles
     */
    private Collection<Style> styles;

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

    /**
     * Returns current {@link DefaultSequence} updated by input array of styles {@link Style}
     *
     * @param styles - initial input array of styles {@link Style}
     * @return updated {@link DefaultSequence}
     */
    public DefaultSequence add(final Style... styles) {
        Arrays.asList(Optional.ofNullable(styles).orElse(new Style[0])).stream().forEach(getStyles()::add);
        return this;
    }

    /**
     * Returns new {@link DefaultSequence} instance by input parameters
     *
     * @param metaData - initial input sequence meta data {@link MetaData}
     * @param styles   - initial input array of styles {@link Style}
     * @return new {@link DefaultSequence} instance
     */
    public static DefaultSequence getSequence(final MetaData metaData, final Style... styles) {
        return DefaultSequence.builder()
            .metaData(metaData)
            .styles(Arrays.asList(Optional.ofNullable(styles).orElse(new Style[0])))
            .build();
    }
}
