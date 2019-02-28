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
package com.wildbeeslabs.sensiblemetrics.ansifancy.decorator;

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.DefaultConfiguration;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.Parser;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.impl.DefaultCharSequenceParser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Default decorator implementation
 */
@Data
//@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DefaultDecorator<T extends CharSequence, R> {

    /**
     * Default parser instance {@link Parser}
     */
    private final Parser<T, R> parser;
    /**
     * Default configuration instance {@link DefaultConfiguration}
     */
    private final DefaultConfiguration configuration;

    /**
     * Default fancy decorator constructor with initial parser {@link Parser} and configuration {@link DefaultConfiguration}
     *
     * @param parser        - initial input parser instance {@link Parser}
     * @param configuration - initial input configuration instance {@link DefaultConfiguration}
     */
    public DefaultDecorator(final Parser<T, R> parser, final DefaultConfiguration configuration) {
        this.parser = parser;
        this.configuration = configuration;
    }

    //public static final AnsiScape ansi = new AnsiScape();

    public R format(final T source, final Object... args) {
        final Parser parser = new DefaultCharSequenceParser(getConfiguration(), source);
        return (R) String.format(getParser().parse(source).toString(), args);
    }
}
