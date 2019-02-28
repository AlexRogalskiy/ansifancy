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
package com.wildbeeslabs.sensiblemetrics.ansifancy.exception;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Style;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Style parser runtime exception {@link RuntimeException}
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StyleParserException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2408356002846172868L;

    /**
     * Default {@link StyleParserException} constructor with initial exception message
     *
     * @param message - initial input exception message {@link String}
     */
    public StyleParserException(final String message) {
        super(message);
    }

    /**
     * Default {@link StyleParserException} constructor with initial exception cause {@Link Throwable}
     *
     * @param cause - initial input exception cause {@Link Throwable}
     */
    public StyleParserException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link StyleParserException} constructor with initial exception message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@Link Throwable}
     */
    public StyleParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link StyleParserException} instance by unknown point {@link Point}
     *
     * @param point - initial input point {@Link Point}
     * @return {@link StyleParserException} instance
     */
    public static final StyleParserException unknownPoint(final Point point) {
        return new StyleParserException(String.format("ERROR: unknown point={%s} detected", point));
    }

    /**
     * Returns {@link StyleParserException} instance by invalid (empty or null) point {@link Point}
     *
     * @return {@link StyleParserException} instance
     */
    public static final StyleParserException invalidPoint() {
        return new StyleParserException(String.format("ERROR: invalid point, cannot be NULL or empty."));
    }

    /**
     * Returns {@link StyleParserException} instance by invalid (empty or null) style {@link Style}
     *
     * @return {@link StyleParserException} instance
     */
    public static final StyleParserException invalidStyle() {
        return new StyleParserException("ERROR: invalid style, cannot be NULL or empty.");
    }
}
