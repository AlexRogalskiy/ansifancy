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

import com.sensiblemetrics.ansifancy.model.iface.PositionIF;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Position {@link RuntimeException} exception
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PositionException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 3382349137530920404L;

    /**
     * Default {@link PositionException} constructor with initial message
     *
     * @param message - initial input exception message {@link String}
     */

    public PositionException(final String message) {
        super(message);
    }

    /**
     * Default {@link PositionException} constructor with initial cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public PositionException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link PositionException} constructor with initial message {@link String} and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public PositionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link PositionException} by input position {@link PositionIF}
     *
     * @param position - initial input position {@link PositionIF}
     * @return {@link PositionException}
     */
    public static <T> PositionException throwUnknownPosition(final PositionIF<T> position) {
        return new PositionException(String.format("ERROR: unknown position: {%s}", position));
    }

    /**
     * Returns {@link PositionException} by input position {@link PositionIF}
     *
     * @param position - initial input position {@link PositionIF}
     * @return {@link PositionException}
     */
    public static <T> PositionException throwIncorrectPosition(final PositionIF<T> position) {
        return new PositionException(String.format("ERROR: incorrect position: {%s}", position));
    }

    /**
     * Returns {@link PositionException} by invalid (empty or null) {@link PositionIF}
     *
     * @return {@link PositionException}
     */
    public static PositionException throwInvalidPosition() {
        return new PositionException("ERROR: invalid position, cannot be NULL or empty");
    }
}
