/*
 * The MIT License
 *
 * Copyright 2019 SensibleMetrics Labs, Inc.
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
package com.sensiblemetrics.ansifancy.exception;

import com.sensiblemetrics.ansifancy.model.iface.PointIF;
import com.sensiblemetrics.ansifancy.model.iface.StyleIF;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Style {@link RuntimeException} exception
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class StyleException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2408356002846172868L;

    /**
     * Default {@link StyleException} constructor with initial message
     *
     * @param message - initial input exception message {@link String}
     */
    public StyleException(final String message) {
        super(message);
    }

    /**
     * Default {@link StyleException} constructor with initial cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public StyleException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link StyleException} constructor with initial message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public StyleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link StyleException} instance by input point {@link PointIF}
     *
     * @param point - initial input point {@link PointIF}
     * @return {@link StyleException} instance
     */
    public static final StyleException throwUnknownPoint(final PointIF point) {
        return new StyleException(String.format("ERROR: unknown point: {%s}", point));
    }

    /**
     * Returns {@link StyleException} instance by invalid (empty or null) point {@link PointIF}
     *
     * @return {@link StyleException} instance
     */
    public static final StyleException throwInvalidPoint() {
        return new StyleException(String.format("ERROR: invalid point, cannot be NULL or empty"));
    }

    /**
     * Returns {@link StyleException} instance by input style {@link StyleIF}
     *
     * @param style - initial input style {@link StyleIF}
     * @return {@link StyleException} instance
     */
    public static final StyleException throwUnknownStyle(final StyleIF style) {
        return new StyleException(String.format("ERROR: unknown style: {%s}", style));
    }

    /**
     * Returns {@link StyleException} instance by invalid (empty or null) style {@link StyleIF}
     *
     * @return {@link StyleException} instance
     */
    public static final StyleException throwInvalidStyle() {
        return new StyleException("ERROR: invalid style, cannot be NULL or empty");
    }
}
