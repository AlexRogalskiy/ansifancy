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

import com.wildbeeslabs.sensiblemetrics.ansifancy.model.Point;
import com.wildbeeslabs.sensiblemetrics.ansifancy.processor.Processor;
import lombok.NoArgsConstructor;

/**
 * Default {@link Point} processor implementation {@link Processor}
 *
 * @param <T> type of element to be processed
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@NoArgsConstructor
public class PointProcessor<T> implements Processor<T, Point> {

    /**
     * Returns {@link Iterable} collection of processed values {@link Point} by input argument value {@code T}
     *
     * @param value - initial input argument value {@code T}
     * @return {@link Iterable} collection of processed values {@link Point}
     */
    @Override
    public <S extends Iterable<? extends Point>> S process(final T value) {
        //        for (final Marker marker : markers) {
//            if (marker instanceof EscapeClassBegin) {
//                // Verify if the escape class is registered in the context
//                EscapeClassBegin begin = (EscapeClassBegin) token;
//                String escapeClassName = begin.getEscapeClassName();
//                AnsiClass ansiClass = ansiScapeContext.get(escapeClassName);
//                if (null == ansiClass) {
//                    throw unknownEscapeClass(escapeClassName);
//                }
//                buff.append(ansiClass.getCharSequences());
//                ansiClasses.add(ansiClass);
//            } else if (token instanceof FreeText) {
//                buff.append(((FreeText) token).getText());
//            } else if (token instanceof EscapeClassEnd) {
//                if (ansiClasses.isEmpty()) {
//                    throw invalidBracketSerquence(token.getStartIndex());
//                }
//                ansiClasses.removeLast();
//                buff.append(AnsiSequence.RESET.getSequence());
//                for (AnsiClass ansiClass : ansiClasses) {
//                    buff.append(ansiClass.getCharSequences());
//                }
//            }
//        }
        return null;
    }
}
