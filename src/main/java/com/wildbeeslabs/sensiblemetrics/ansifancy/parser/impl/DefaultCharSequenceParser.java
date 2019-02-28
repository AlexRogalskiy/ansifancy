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
import com.wildbeeslabs.sensiblemetrics.ansifancy.exception.MarkerException;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Marker;
import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Sequence;
import com.wildbeeslabs.sensiblemetrics.ansifancy.parser.Parser;
import com.wildbeeslabs.sensiblemetrics.ansifancy.processor.Processor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Default parser implementation {@link Parser}
 *
 * @param <R> type of element to be parsed to
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Data
public class DefaultCharSequenceParser<R extends CharSequence> implements Parser<CharSequence, R> {

    /**
     * Default character escape symbols
     */
    private static final char ESCAPE_SYMBOL = '`';
    private static final char ESCAPE_CLASS_BEGIN_SYMBOL = '{';
    private static final char ESCAPE_CLASS_END_SYMBOL = '}';

    /**
     * Default input configuration {@link DefaultConfiguration}
     */
    private final DefaultConfiguration configuration;
    /**
     * Default processor instance {@link Processor}
     */
    private final Processor<CharSequence, Marker> processor;

    /**
     * Default parser constructor with input configuration {@link DefaultConfiguration}
     *
     * @param configuration - initial input configuration {@link DefaultConfiguration}
     */
    public DefaultCharSequenceParser(final DefaultConfiguration configuration, final Processor<CharSequence, Marker> processor) {
        this.configuration = configuration;
        this.processor = processor;
    }

    /**
     * Returns parsed value {@code R} by input argument value {@code T}
     *
     * @param value - initial input argument value {@code T}
     * @return parsed value {@code R}
     */
    @Override
    public R parse(final CharSequence value) {
        if (StringUtils.isEmpty(value)) {
            throw MarkerException.invalidSource();
        }
        final StringBuilder buff = new StringBuilder();
        final List<Marker> markers = getProcessor().process(value);
        final LinkedList<Sequence> ansiClasses = new LinkedList<>();
        for (final Marker marker : markers) {
            if (marker instanceof EscapeClassBegin) {
                // Verify if the escape class is registered in the context
                EscapeClassBegin begin = (EscapeClassBegin) token;
                String escapeClassName = begin.getEscapeClassName();
                AnsiClass ansiClass = ansiScapeContext.get(escapeClassName);
                if (null == ansiClass) {
                    throw unknownEscapeClass(escapeClassName);
                }
                buff.append(ansiClass.getCharSequences());
                ansiClasses.add(ansiClass);
            } else if (token instanceof FreeText) {
                buff.append(((FreeText) token).getText());
            } else if (token instanceof EscapeClassEnd) {
                if (ansiClasses.isEmpty()) {
                    throw invalidBracketSerquence(token.getStartIndex());
                }
                ansiClasses.removeLast();
                buff.append(AnsiSequence.RESET.getSequence());
                for (AnsiClass ansiClass : ansiClasses) {
                    buff.append(ansiClass.getCharSequences());
                }
            }
        }
        return (R) buff.toString();
    }
}
