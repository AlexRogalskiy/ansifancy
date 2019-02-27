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
@NoArgsConstructor
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
     * Default point code
     */
    private CharSequence code;
    /**
     * Default view code
     */
    private CharSequence view;

    /**
     * Returns new {@link DefaultPoint} instance by input escape value {@link CharSequence}
     *
     * @param escape - initial input escape value {@link CharSequence}
     * @return new {@link DefaultPoint} instance
     */
    public static DefaultPoint getPoint(@NonNull final CharSequence code, @NonNull final CharSequence escape) {
        if (isEmpty(escape)) {
            throw new IllegalArgumentException(String.format("ERROR: invalid escape sequence={%s}, should not be empty or null", escape));
        }
        return DefaultPoint.builder().code(code).view(String.format(DEFAULT_ESCAPE_FORMAT, escape)).build();
    }
}
