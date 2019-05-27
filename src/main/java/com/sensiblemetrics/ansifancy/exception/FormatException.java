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

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Format {@link RuntimeException} exception
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FormatException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 6535141661569846794L;

    /**
     * Default {@link MarkerException} constructor with initial message
     *
     * @param message - initial input exception message {@link String}
     */

    public FormatException(final String message) {
        super(message);
    }

    /**
     * Default {@link MarkerException} constructor with initial cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public FormatException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link MarkerException} constructor with initial message {@link String} and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public FormatException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link FormatException} by input number of arguments
     *
     * @param numOfArgs - initial input number of arguments
     * @return {@link FormatException}
     */
    public static final FormatException throwInvalidNumOfArguments(final int numOfArgs) {
        return new FormatException(String.format("ERROR: invalid number of arguments: {%s}", numOfArgs));
    }

    /**
     * Returns {@link FormatException} by input source path {@link CharSequence} and error {@link Throwable}
     *
     * @return {@link FormatException}
     */
    public static final FormatException throwIOError(final CharSequence path, final Throwable throwable) {
        return new FormatException(String.format("ERROR: cannot process source path: {%s}", path), throwable);
    }

    /**
     * Returns {@link FormatException} by input index value
     *
     * @return {@link FormatException}
     */
    public static FormatException throwInvalidPositionArgument(int index) {
        return new FormatException(String.format("ERROR: cannot process argument at position: {%s}", index));
    }

    /**
     * Returns {@link FormatException} by input argument name {@link CharSequence}
     *
     * @return {@link FormatException}
     */
    public static FormatException throwInvalidArgumentName(final CharSequence argName) {
        return new FormatException(String.format("ERROR: cannot process argument by name: {%s}", argName));
    }
}
