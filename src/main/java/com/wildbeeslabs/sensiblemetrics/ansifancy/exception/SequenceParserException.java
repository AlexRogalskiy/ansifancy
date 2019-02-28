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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Sequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Style;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Marker parser runtime exception {@link RuntimeException}
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SequenceParserException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2408356002846172868L;

    /**
     * Default {@link SequenceParserException} constructor with initial exception message
     *
     * @param message - initial input exception message {@link String}
     */
    public SequenceParserException(final String message) {
        super(message);
    }

    /**
     * Default {@link SequenceParserException} constructor with initial exception cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public SequenceParserException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link SequenceParserException} constructor with initial exception message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public SequenceParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link SequenceParserException} instance by unknown style {@link Style}
     *
     * @param style - initial input symbol style {@link Style}
     * @return {@link SequenceParserException} instance
     */
    public static final SequenceParserException unknownStyle(final Style style) {
        return new SequenceParserException(String.format("ERROR: unknown style={%s} detected", style));
    }

    /**
     * Returns {@link SequenceParserException} instance by invalid (empty or null) style {@link Style}
     *
     * @return {@link SequenceParserException} instance
     */
    public static final SequenceParserException invalidStyle() {
        return new SequenceParserException(String.format("ERROR: invalid stu;e, cannot be NULL or empty."));
    }

    /**
     * Returns {@link SequenceParserException} instance by invalid (empty or null) symbol {@link Sequence}
     *
     * @return {@link SequenceParserException} instance
     */
    public static final SequenceParserException invalidSequence() {
        return new SequenceParserException("ERROR: invalid sequence, cannot be NULL or empty.");
    }
}
