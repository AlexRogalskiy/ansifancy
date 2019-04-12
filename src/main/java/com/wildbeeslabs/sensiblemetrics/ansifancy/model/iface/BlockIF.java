package com.wildbeeslabs.sensiblemetrics.ansifancy.model.iface;

import java.io.Serializable;

/**
 * Block interface declaration
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
public interface BlockIF<T> extends Serializable, Cloneable {

    /**
     * Returns {@link BlockIF} top right position
     *
     * @return {@link BlockIF} top right position
     */
    <S extends AreaIF<T>> S getArea();
}
