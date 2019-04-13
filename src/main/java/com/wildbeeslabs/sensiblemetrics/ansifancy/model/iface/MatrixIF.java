package com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface;

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
