package com.sensiblemetrics.ansifancy.model.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Default coordinate implementation
 *
 * @author Alexander Rogalskiy
 * @version 1.0
 */
@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
public class IntCoordinate implements Comparable<IntCoordinate> {

    /**
     * Coordinate value
     */
    private int value;

    @Override
    public int compareTo(final IntCoordinate o) {
        if (this.getValue() == o.getValue()) return 0;
        return this.getValue() < o.getValue() ? -1 : 1;
    }
}
