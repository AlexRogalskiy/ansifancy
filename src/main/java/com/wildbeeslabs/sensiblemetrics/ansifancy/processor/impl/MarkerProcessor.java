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
package com.wildbeeslabs.sensiblemetrics.ansifancy.processor.impl;

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Marker;
import com.wildbeeslabs.sensiblemetrics.ansifancy.processor.Processor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * Default {@link Marker} processor implementation {@link Processor}
 *
 * @param <T> type of element to be parsed
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@NoArgsConstructor
public class MarkerProcessor<T> implements Processor<T, Marker> {

    /**
     * Returns {@link Iterable} collection of processed values {@link Marker} by input argument value {@code T}
     *
     * @param value - initial input argument value {@code T}
     * @return {@link Iterable} collection of processed values {@link Marker}
     */
    @Override
    public <R extends Iterable<? extends Marker>> R process(final T value) {
        final List<Marker> tokens = new LinkedList<>();
        int i = 0;
        char now;
        final StringBuilder freeText = new StringBuilder();
        while (i < source.length()) {
            now = source.charAt(i);
            // Escape char detected - ignoring next character
            if (now == CHR_ESCAPE) {
                freeText.append(source.charAt(i + 1));
                i++;
            } else if (now == CHR_ESCAPE_CLASS_BEGIN) {
                // This is where we exit from potential FreeText so everything we collected
                // so far is going to be added in the final list of tokens
                appendExistingFreeText(tokens, freeText, i);
                // We obtain the ascii escape class name
                String escapeClassName = ParserUtils.getUntilSpaceOrEnd(source, i + 1);
                tokens.add(new EscapeClassBegin(i, escapeClassName));
                // We skip to the character where the class name ends.
                i += escapeClassName.length() + 1;
            } else if (now == CHR_ESCAPE_CLASS_END) {
                // This is where we exit from potential FreeText so everything we collected
                // so far is going to be added in the final list of tokens
                appendExistingFreeText(tokens, freeText, i);
                tokens.add(new EscapeClassEnd(i));
            } else {
                freeText.append(now);
            }
            i++;
        }
        // If there was anything in the freetext buffer we add it to the list of tokens
        appendExistingFreeText(tokens, freeText, i);
        return tokens;
    }

    public void appendExistingFreeText(final List<Token> tokens, StringBuilder freeTextBuff, int idx) {
        if (freeTextBuff.length() != 0) {
            tokens.add(new FreeText(idx, freeTextBuff.toString()));
            freeTextBuff.setLength(0);
        }
    }
}
