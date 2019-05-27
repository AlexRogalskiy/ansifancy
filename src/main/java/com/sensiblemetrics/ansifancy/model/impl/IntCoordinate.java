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

    /**
     * Return integer result by {@link IntCoordinate}s comparison
     *
     * @param coordinate - initial input {@link IntCoordinate} to compare by
     * @return 0 - if {@link IntCoordinate}s are equals, -1 - if current {@link IntCoordinate} is lower than input {@link IntCoordinate}, 1 - if current {@link IntCoordinate} is greater than {@link IntCoordinate}
     */
    @Override
    public int compareTo(final IntCoordinate coordinate) {
        if (this.getValue() == coordinate.getValue()) return 0;
        return this.getValue() < coordinate.getValue() ? -1 : 1;
    }
}
