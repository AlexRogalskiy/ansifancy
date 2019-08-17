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
package com.sensiblemetrics.ansifancy.decorator.impl;

import com.sensiblemetrics.ansifancy.config.iface.ConfigurationIF;
import com.sensiblemetrics.ansifancy.decorator.iface.DecoratorIF;
import com.sensiblemetrics.ansifancy.model.iface.MarkerIF;
import com.sensiblemetrics.ansifancy.parser.iface.ParserIF;
import com.sensiblemetrics.ansifancy.parser.impl.CharSequenceParser;
import com.sensiblemetrics.ansifancy.processor.iface.ProcessorIF;
import com.sensiblemetrics.ansifancy.utils.ValidationUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Default {@link CharSequence} parser implementation {@link DecoratorIF}
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
        ValidationUtils.notNull(configuration, "Should not be null or empty");
        ValidationUtils.notNull(processor, "Should not be null or empty");
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
    public R apply(@NonNull final CharSequence source, @NonNull final Object... args) {
        return (R) String.format(getParser().parse(source).toString(), args);
    }
}
