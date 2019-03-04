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

import com.wildbeeslabs.sensiblemetrics.ansifancy.config.ConfigurationIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.decorator.DecoratorIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.MarkerIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.ParserIF;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.impl.CharSequenceParser;
import com.wildbeeslabs.sensiblemetrics.ansifancy.processor.ProcessorIF;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

/**
 * Default parser decorator implementation {@link DecoratorIF}
 */
@Data
@EqualsAndHashCode
@ToString
public class CharSequenceParserDecorator<R extends CharSequence> implements DecoratorIF<CharSequence, R> {

    /**
     * Default parser instance {@link ParserIF}
     */
    private final ParserIF<CharSequence, R> parser;

    /**
     * Default {@link CharSequenceParserDecorator} constructor by initial {@link ConfigurationIF} instance
     *
     * @param configuration - initial input {@link ConfigurationIF} instance
     */
    public CharSequenceParserDecorator(final ConfigurationIF configuration, final ProcessorIF<CharSequence, MarkerIF> processor) {
        Objects.requireNonNull(configuration, "Should not be null or empty");
        Objects.requireNonNull(processor, "Should not be null or empty");
        this.parser = new CharSequenceParser(configuration, processor);
    }

    /**
     * Returns value {@code R} decorated by input source {@code T} and array of parameters
     *
     * @param source - initial input source {@code T} to be parsed
     * @param args   - initial input array of objects {@link Object} to parse by
     * @return decorated value {@code R}
     */
    @Override
    public R decorate(final CharSequence source, final Object... args) {
        return (R) String.format(getParser().parse(source).toString(), args);
    }
}
