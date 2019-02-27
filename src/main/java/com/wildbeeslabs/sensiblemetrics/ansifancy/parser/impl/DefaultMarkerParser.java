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

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.AnsiFancyConfiguration;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.Parser;
import lombok.Getter;

/**
 * Marker parser implementation {@link javax.swing.text.html.parser.Parser}
 *
 * @param <T> type of element to be stored by entry
 * @author Alexander Rogalskiy
 * @version 1.1
 * @since 1.0
 */
public class DefaultMarkerParser<T extends CharSequence, R extends CharSequence> implements Parser<T, R> {

    private static final char CHR_ESCAPE = '`';
    private static final char CHR_ESCAPE_CLASS_BEGIN = '{';
    private static final char CHR_ESCAPE_CLASS_END = '}';

    @Getter
    private AnsiFancyConfiguration configuration = new AnsiFancyConfiguration();

    @Override
    public R parse(final T value) {
        return null;
    }
}
