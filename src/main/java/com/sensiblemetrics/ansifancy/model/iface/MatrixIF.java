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

import java.io.Serializable;

/**
 * Matrix interface declaration
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public interface MatrixIF<T> extends Serializable {

    /**
     * Returns {@link MatrixIF} width
     *
     * @return width of current {@link MatrixIF}
     */
    int width();

    /**
     * Returns {@link MatrixIF} height
     *
     * @return height of current {@link MatrixIF}
     */
    int height();

    /**
     * Returns array of {@link StyleIF} by input row index
     *
     * @param i - initial input row index
     * @return array of {@link StyleIF}
     */
    T[] getRow(int i);

    /**
     * Returns array of {@link StyleIF} by input column index
     *
     * @param j - initial input column index
     * @return array of {@link StyleIF}
     */
    T[] getColumn(int j);

    /**
     * Returns {@code T} by input row / column parameters
     *
     * @param i - initial input row value
     * @param j - initial input column value
     * @return {@code T}
     */
    T get(int i, int j);

    /**
     * Updates {@code T} by input row / column parameters
     *
     * @param i     - initial input row value
     * @param j     - initial input column value
     * @param value - initial input {@code T} instance
     */
    void set(int i, int j, final T value);
}
