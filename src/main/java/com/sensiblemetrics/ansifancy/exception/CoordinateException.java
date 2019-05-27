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

import com.sensiblemetrics.ansifancy.model.impl.IntCoordinate;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Position {@link RuntimeException} exception
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CoordinateException extends RuntimeException {

    /**
     * Default {@link CoordinateException} constructor with initial message
     *
     * @param message - initial input exception message {@link String}
     */

    public CoordinateException(final String message) {
        super(message);
    }

    /**
     * Default {@link CoordinateException} constructor with initial cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public CoordinateException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link CoordinateException} constructor with initial message {@link String} and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public CoordinateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link CoordinateException} by input {@link IntCoordinate}
     *
     * @param coordinate - initial input {@link IntCoordinate}
     * @return {@link CoordinateException}
     */
    public static CoordinateException throwIncorrectCoordinate(final IntCoordinate coordinate) {
        return new CoordinateException(String.format("ERROR: incorrect coordinate: {%s}", coordinate));
    }

    /**
     * Returns {@link CoordinateException} by invalid (empty or null) {@link IntCoordinate}
     *
     * @return {@link CoordinateException}
     */
    public static CoordinateException throwInvalidCoordinate() {
        return new CoordinateException("ERROR: invalid coordinate, cannot be NULL or empty");
    }
}
