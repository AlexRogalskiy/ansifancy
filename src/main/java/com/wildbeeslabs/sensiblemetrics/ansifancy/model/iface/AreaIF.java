package com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface;

import java.io.Serializable;

/**
 * Area interface declaration
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public interface AreaIF<T> extends Serializable {

    /**
     * Returns {@link BlockIF} top right position {@code T}
     *
     * @return {@link BlockIF} top right position {@code T}
     */
    <S extends PositionIF<T>> S getTopRight();

    /**
     * Returns {@link BlockIF} bottom left position {@code T}
     *
     * @return {@link BlockIF} bottom left position {@code T}
     */
    <S extends PositionIF<T>> S getBottomLeft();

    /**
     * Returns width of current {@link AreaIF}
     *
     * @return width of current {@link AreaIF}
     */
    int width();

    /**
     * Returns height of current {@link AreaIF}
     *
     * @return height of current {@link AreaIF}
     */
    int height();
}
