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
package com.wildbeeslabs.sensiblemetrics.ansifancy.decorator.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.Configuration;
import com.wildbeeslabs.sensiblemetrics.ansifancy.decorator.DecoratorIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.ParserIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.impl.CharSequenceParser;
import com.wildbeeslabs.sensiblemetrics.ansifancy.processor.impl.MarkerProcessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

/**
 * Default decorator implementation {@link DecoratorIF}
 */
@Data
@EqualsAndHashCode
@ToString
public class CharSequenceDecorator<T extends CharSequence, R> implements DecoratorIF<T, R> {

    /**
     * Default parser instance {@link ParserIF}
     */
    private final ParserIF<T, R> parser;

    /**
     * Default (@link CharSequenceDecorator) constructor by initial {@link Configuration} instance
     *
     * @param configuration - initial input {@link Configuration} instance
     */
    public CharSequenceDecorator(final Configuration configuration) {
        Objects.requireNonNull(configuration);
        this.parser = new CharSequenceParser(configuration, new MarkerProcessor<>());
    }

    /**
     * Returns value {@code R} decorated by input source {@code T} and array of objects
     *
     * @param source - initial input source {@code T} to be decorated
     * @param args   - initial input array of objects {@link Object} to decorate by
     * @return decorated value {@code R}
     */
    @Override
    public R decorate(final T source, final Object... args) {
        return (R) String.format(getParser().parse(source).toString(), args);
    }
}
