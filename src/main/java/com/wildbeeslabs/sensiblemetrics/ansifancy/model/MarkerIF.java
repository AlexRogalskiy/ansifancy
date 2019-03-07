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
package com.wildbeeslabs.sensiblemetrics.ansifancy.model;

import java.io.Serializable;

/**
 * Marker interface declaration
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public interface MarkerIF extends Serializable {

    /**
     * Returns {@link PositionIF} instance
     *
     * @return {@link PositionIF} instance
     */
    PositionIF getPosition();

    /**
     * Returns {@link MetaDataIF} instance
     *
     * @return {@link MetaDataIF} instance
     */
    MetaDataIF getMetaData();

    /**
     * Returns {@code S} collection of {@link StyleIF} instances
     *
     * @param <S> type of {@link Iterable} item collection
     * @return collection of {@link StyleIF} instances
     */
    <S extends Iterable<? extends StyleIF>> S getStyles();
}
