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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Position;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Position parser runtime exception {@link RuntimeException}
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PositionParserException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 3382349137530920404L;

    /**
     * Default {@link PositionParserException} constructor with initial exception message
     *
     * @param message - initial input exception message {@link String}
     */

    public PositionParserException(final String message) {
        super(message);
    }

    /**
     * Default {@link PositionParserException} constructor with initial exception cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public PositionParserException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link PositionParserException} constructor with initial exception message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public PositionParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link PositionParserException} instance by invalid (empty or null) position {@link Position}
     *
     * @return {@link PositionParserException} instance
     */
    public static final PositionParserException invalidPosition() {
        return new PositionParserException("ERROR: invalid position, cannot be NULL or empty.");
    }
}
