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
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Sequence;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Marker parser runtime exception {@link RuntimeException}
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SymbolParserException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2408356002846172868L;

    /**
     * Default {@link SymbolParserException} constructor with initial exception message
     *
     * @param message - initial input exception message {@link String}
     */
    public SymbolParserException(final String message) {
        super(message);
    }

    /**
     * Default {@link SymbolParserException} constructor with initial exception cause {@Link Throwable}
     *
     * @param cause - initial input exception cause {@Link Throwable}
     */
    public SymbolParserException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link SymbolParserException} constructor with initial exception message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@Link Throwable}
     */
    public SymbolParserException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link SymbolParserException} instance by unknown point {@link Point}
     *
     * @param point - initial input symbol point {@link Point}
     * @return {@link SymbolParserException} instance
     */
    public static final SymbolParserException unknownPoint(final Point point) {
        return new SymbolParserException(String.format("ERROR: unknown point={%s} detected", point));
    }

    /**
     * Returns {@link SymbolParserException} instance by invalid style {@link Style}
     *
     * @param style - initial input symbol style {@Link Style}
     * @return {@link SymbolParserException} instance
     */
    public static final SymbolParserException invalidStyle(final Style style) {
        return new SymbolParserException(String.format("ERROR: invalid style={%s} detected", style));
    }

    /**
     * Returns {@link SymbolParserException} instance by invalid (empty or null) symbol {@link Sequence}
     *
     * @return {@link SymbolParserException} instance
     */
    public static final SymbolParserException invalidSymbol() {
        return new SymbolParserException("ERROR: invalid symbol, cannot be NULL or empty.");
    }
}
