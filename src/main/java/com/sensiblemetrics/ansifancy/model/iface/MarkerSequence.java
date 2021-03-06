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
package com.sensiblemetrics.ansifancy.model.iface;

import lombok.NonNull;

import java.io.Serializable;
import java.util.Map;

/**
 * Marker {@link CharSequence} interface declaration
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public interface MarkerSequence extends CharSequence, Serializable {

    /**
     * Empty {@link MarkerSequence}
     */
    MarkerSequence EMPTY = new EmptyMarkerSequence();

    /**
     * Returns {@link MarkerSequence} by input array of {@link StyleIF}
     *
     * @param <S>    type of style item
     * @param styles - initial input array of {@link StyleIF}
     * @return {@link MarkerSequence} value
     */
    @NonNull <S extends StyleIF> MarkerSequence styles(final S... styles);

    @NonNull <C extends CharSequence> MarkerSequence args(final Map<C, Object> args);

    /**
     * Default empty {@link MarkerSequence} implementation
     */
    final class EmptyMarkerSequence implements MarkerSequence {

        @Override
        public @NonNull <S extends StyleIF> MarkerSequence styles(final S... styles) {
            return null;
        }

        @Override
        public @NonNull <C extends CharSequence> MarkerSequence args(final Map<C, Object> args) {
            return null;
        }

        @Override
        public int length() {
            return 0;
        }

        @Override
        public char charAt(int index) {
            return 0;
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return null;
        }
    }
}
