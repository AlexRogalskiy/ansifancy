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
package com.wildbeeslabs.sensiblemetrics.ansifancy.parser.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.DefaultConfiguration;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.Parser;

/**
 * Default parser implementation {@link Parser}
 *
 * @param <T> type of element to be parsed
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public class DefaultParser<T extends CharSequence, R extends CharSequence> implements Parser<T, R> {

    /**
     * Default character escape sequences
     */
    private static final char CHR_ESCAPE = '`';
    private static final char CHR_ESCAPE_CLASS_BEGIN = '{';
    private static final char CHR_ESCAPE_CLASS_END = '}';

    /**
     * Default input configuration {@link DefaultConfiguration}
     */
    private DefaultConfiguration configuration;
    /**
     * Default input source {@code T}
     */
    private T source;

    /**
     * Default parser constructor with input configuration {@link DefaultConfiguration} and source {@link String}
     *
     * @param configuration - initial input configuration {@link DefaultConfiguration}
     * @param source        - initial input source {@link String}
     */
    public DefaultParser(final DefaultConfiguration configuration, final T source) {
        this.configuration = configuration;
        this.source = source;
    }

    @Override
    public R parse(final T value) {
        return null;
    }
}
