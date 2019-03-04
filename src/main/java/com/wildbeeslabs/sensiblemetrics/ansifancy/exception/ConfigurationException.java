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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MetaDataIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.StyleIF;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Configuration runtime exception {@link RuntimeException}
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConfigurationException extends RuntimeException {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 2802569224038657627L;

    /**
     * Default {@link ConfigurationException} constructor with initial message
     *
     * @param message - initial input exception message {@link String}
     */

    public ConfigurationException(final String message) {
        super(message);
    }

    /**
     * Default {@link ConfigurationException} constructor with initial cause {@link Throwable}
     *
     * @param cause - initial input exception cause {@link Throwable}
     */
    public ConfigurationException(final Throwable cause) {
        super(cause);
    }

    /**
     * Default {@link ConfigurationException} constructor with initial message and cause {@link Throwable}
     *
     * @param message - initial input exception message {@link String}
     * @param cause   - initial input exception cause {@link Throwable}
     */
    public ConfigurationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Returns {@link ConfigurationException} instance by already registered meta data {@link StyleIF}
     *
     * @return {@link ConfigurationException} instance
     */
    public static final ConfigurationException alreadyDefined(final MetaDataIF metaData) {
        return new ConfigurationException(String.format("ERROR: already defined meta data={%s}, should be unique.", metaData));
    }
}
